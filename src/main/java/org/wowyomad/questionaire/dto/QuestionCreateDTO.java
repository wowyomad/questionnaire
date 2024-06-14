package org.wowyomad.questionaire.dto;

import lombok.Data;
import org.wowyomad.questionaire.model.Question;

import java.util.List;

@Data
public class QuestionCreateDTO {
    private String label;
    private Question.QuestionType type;
    private boolean required;
    private boolean isActive;
    private List<OptionCreateDTO> options;
}
