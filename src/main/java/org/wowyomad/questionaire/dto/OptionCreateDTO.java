package org.wowyomad.questionaire.dto;

import lombok.Data;

@Data
public class OptionCreateDTO {
    private String label;
    private Long index;
}