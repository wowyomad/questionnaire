package org.wowyomad.questionaire.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AnswerDto {
    private Integer id;

    @NotNull
    private Integer questionId;

    @Nullable
    private String text;

    @Nullable
    private List<OptionDTO> selectedOptions;
}
