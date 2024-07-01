package org.wowyomad.questionaire.utils.validation;

import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Question;

class TextQuestionValidator extends AbstractQuestionValidator {
    @Override
    public boolean validate(QuestionDto question) {
        return question.getLabel() != null && !question.getLabel().isBlank() &&
                (question.getOptions() == null || question.getOptions().isEmpty());
    }

    @Override
    public boolean validateAnswer(AnswerDto answer, Question question) {
        return answer.getText() != null && !answer.getText().isBlank()
                &&
                (answer.getSelectedOptions() == null || answer.getSelectedOptions().isEmpty());

    }
}
