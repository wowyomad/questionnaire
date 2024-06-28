package org.wowyomad.questionaire.utils.validation;

import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Question;


abstract class AbstractQuestionValidator {
    public abstract boolean validate(QuestionDto question);
    public abstract boolean validateAnswer(AnswerDto answer, Question question);
}
