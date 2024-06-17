package org.wowyomad.questionaire.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OptionDTO {

    private Integer id;

    @NotNull
    private Integer index;

    @NotNull
    @NotBlank
    private String text;

    private Integer questionId;
}
