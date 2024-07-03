package org.wowyomad.questionaire.service;

import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.dto.UserPasswordResetDto;
import org.wowyomad.questionaire.utils.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    UserDto getUser(Integer id) throws UserNotFoundException;

    AuthenticationResponse updateUser(Integer id, UserDto updatedUser);

    AuthenticationResponse patchUser(Integer id, UserDto patchUser);

    UserDto updatePassword(Integer id, UserPasswordResetDto password);

    List<UserDto> getAllUsers();
}
