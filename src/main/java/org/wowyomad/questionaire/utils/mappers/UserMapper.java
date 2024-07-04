package org.wowyomad.questionaire.utils.mappers;

import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.UserDto;
import org.wowyomad.questionaire.model.User;
import org.wowyomad.questionaire.utils.enums.Role;

@Component
public class UserMapper {
    public User mapToEntity(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                null,
                userDto.getPhone(),
                Role.USER
        );
    }

    public UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone()
        );
    }
}
