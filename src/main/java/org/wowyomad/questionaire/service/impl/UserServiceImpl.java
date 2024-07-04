package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.dto.UserPasswordChangeDto;
import org.wowyomad.questionaire.model.User;
import org.wowyomad.questionaire.repository.UserRepository;
import org.wowyomad.questionaire.service.JwtService;
import org.wowyomad.questionaire.service.MailService;
import org.wowyomad.questionaire.service.UserService;
import org.wowyomad.questionaire.utils.exceptions.UserNotFoundException;
import org.wowyomad.questionaire.utils.exceptions.UserWrongPasswordException;
import org.wowyomad.questionaire.utils.mappers.UserMapper;
import org.wowyomad.questionaire.utils.patchers.UserPatcher;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MailService mailService;
    private final UserPatcher userPatcher;


    @Override
    public UserDto getUser(Integer id) throws UserNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with id %d", id)));
        return userMapper.mapToDto(user);
    }


    @Override
    public AuthenticationResponse patchUser(Integer id, UserDto patchUserDto)  {
        User userToPatch = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with id %d", id)));

        String email = userToPatch.getEmail();

        User patchUser = userMapper.mapToEntity(patchUserDto);
        userPatcher.patch(userToPatch, patchUser);

        User patchedUser = userRepository.save(userToPatch);
        String jwtToken = jwtService.generateToken(patchedUser);


        if(!patchedUser.getEmail().equals(email)) {
            String message = String.format("""
                        <html>
                        <head></head>
                        <body>
                            <h1>Email changed!</h1>
                            <h2>Your new email: %s</h2>
                        </body>
                        </html>
                        """,
                    patchedUser.getEmail()
            );
            mailService.sendNotifyingMail(email, message);
        }


        return new AuthenticationResponse(jwtToken, patchedUser.getId());
    }

    @Override
    public AuthenticationResponse updatePassword(Integer id, UserPasswordChangeDto password) {
        User user = userRepository.findById(id).
                orElseThrow(() ->new UserNotFoundException(String.format("User not found with id %d", id)));

        if(!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            throw new UserWrongPasswordException("Passwords don't match");

        }

        String newPassword = passwordEncoder.encode(password.getNewPassword());

        user.setPassword(newPassword);
        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(savedUser);

        String message = String.format("""
                        <html>
                        <head></head>
                        <body>
                            <h1>Successfully changed password!</h1>
                            <h2>password: %s</h2>
                        </body>
                        </html>
                        """,
                password.getNewPassword()
        );
        mailService.sendNotifyingMail(savedUser.getEmail(), message);

        return new AuthenticationResponse(jwtToken, savedUser.getId());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::mapToDto).toList();
    }
}
