package org.wowyomad.questionaire.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @Nullable
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

}
