package org.wowyomad.questionaire.dto;

import lombok.Data;
import java.util.List;

@Data
public class AnswerCreateDTO {
    private Long questionId;
    private String response;
    private List<Long> selectedOptions;
}
