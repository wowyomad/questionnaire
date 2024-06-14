package org.wowyomad.questionaire.dto;

import lombok.Data;

@Data
public class QuestionSimpleDTO {
    private Long id;
    private String label;
    private String type;
    private boolean required;
    private boolean isActive;
}
