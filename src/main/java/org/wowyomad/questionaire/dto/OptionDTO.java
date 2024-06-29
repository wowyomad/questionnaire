package org.wowyomad.questionaire.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OptionDTO {

    private Integer id;

    private Integer index;

    @NotNull
    @NotBlank
    private String text;

    private Integer questionId;
}
