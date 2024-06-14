package org.wowyomad.questionaire.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SubmissionCreateDTO {
    private Long id;
    private LocalDateTime submissionTime;
    private List<AnswerCreateDTO> answers;
}
