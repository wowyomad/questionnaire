package org.wowyomad.questionaire.utils.validation;

import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.utils.Values;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

class DateQuestionValidator extends TextQuestionValidator {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Values.datePattern);
    @Override
    public boolean validateAnswer(AnswerDto answer, Question question) {
        if(!super.validateAnswer(answer, question)) return false;

        try {
            dtf.parse(answer.getText());
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }
}
