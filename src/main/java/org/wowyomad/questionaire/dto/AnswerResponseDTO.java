package org.wowyomad.questionaire.dto;

import lombok.Data;
import java.util.List;

@Data
public class AnswerResponseDTO {
    private Long id;
    private Long questionId;
    private String response;
    private List<OptionResponseDTO> selectedOptions;
}
