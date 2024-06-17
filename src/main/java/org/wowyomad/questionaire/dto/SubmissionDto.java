package org.wowyomad.questionaire.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SubmissionDto {
    private Integer id;

    @NotNull
    private List<AnswerDto> answers;
}
