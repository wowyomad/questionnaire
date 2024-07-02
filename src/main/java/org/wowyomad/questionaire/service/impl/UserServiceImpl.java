package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.dto.UserPasswordResetDto;
import org.wowyomad.questionaire.model.User;
import org.wowyomad.questionaire.repository.UserRepository;
import org.wowyomad.questionaire.service.JwtService;
import org.wowyomad.questionaire.service.UserService;
import org.wowyomad.questionaire.utils.exceptions.UserNotFoundException;
import org.wowyomad.questionaire.utils.mappers.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public UserDto getUser(Integer id) throws UserNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with id %d", id)));
        return userMapper.mapToDto(user);
    }

    @Override
    public AuthenticationResponse updateUser(Integer id, UserDto userDto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with id %d", id)));

        User updatedUser = userMapper.mapToEntity(userDto);
        updatedUser.setId(oldUser.getId());
        updatedUser.setPassword(oldUser.getPassword());

        updatedUser = userRepository.save(updatedUser);
        String jwtToken = jwtService.generateToken(updatedUser);

        return new AuthenticationResponse(jwtToken, updatedUser.getId());
    }

    @Override
    public UserDto updatePassword(Integer id, UserPasswordResetDto password) {
        User user = userRepository.findById(id).
                orElseThrow(() ->new UserNotFoundException(String.format("User not found with id %d", id)));

        String newPassword = passwordEncoder.encode(password.getNewPassword());
        user.setPassword(newPassword);
        user = userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::mapToDto).toList();
    }
}
