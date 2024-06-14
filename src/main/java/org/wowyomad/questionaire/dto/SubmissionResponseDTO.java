package org.wowyomad.questionaire.dto;

import java.time.LocalDateTime;
import lombok.Data;

import java.util.List;
@Data
public class SubmissionResponseDTO {
    private Long id;
    private LocalDateTime submissionTime;
    private List<AnswerResponseDTO> answers;
}
