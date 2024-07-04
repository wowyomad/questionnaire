package org.wowyomad.questionaire.service;

import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.dto.UserPasswordChangeDto;
import org.wowyomad.questionaire.utils.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    UserDto getUser(Integer id) throws UserNotFoundException;

    AuthenticationResponse patchUser(Integer id, UserDto patchUser);

    AuthenticationResponse updatePassword(Integer id, UserPasswordChangeDto password);

    List<UserDto> getAllUsers();
}
