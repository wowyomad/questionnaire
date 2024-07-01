package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.AuthenticationRequest;
import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.RegisterRequest;
import org.wowyomad.questionaire.model.User;
import org.wowyomad.questionaire.repository.UserRepository;
import org.wowyomad.questionaire.service.AuthenticationService;
import org.wowyomad.questionaire.service.JwtService;
import org.wowyomad.questionaire.utils.enums.Role;
import org.wowyomad.questionaire.utils.exceptions.UserLoginAlreadyInUseException;
import org.wowyomad.questionaire.utils.mappers.UserMapper;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken, userMapper.mapToDto(user));
    }
@Override
    public AuthenticationResponse register(RegisterRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserLoginAlreadyInUseException(String.format("Email already in use %s", request.getEmail()));
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(Role.USER)
                .build();
        user = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken, userMapper.mapToDto(user));
    }
}
