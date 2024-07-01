package org.wowyomad.questionaire.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AnswerDto {
    private Integer id;

    @NotNull
    private Integer questionId;

    @NotNull
    private String questionType;

    @Nullable
    private String text;

    @Nullable
    private List<OptionDto> selectedOptions;
}
