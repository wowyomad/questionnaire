package org.wowyomad.questionaire.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPasswordResetDto {

    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
