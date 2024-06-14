package org.wowyomad.questionaire.dto;

import lombok.Data;
import org.wowyomad.questionaire.model.Question;

@Data
public class OptionResponseDTO {
    private Long id;
    private String label;
    private Long index;
}
