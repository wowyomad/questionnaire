package org.wowyomad.questionaire.utils.validation;

import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;

import java.util.Set;
import java.util.stream.Collectors;

class SingleChoiceQuestionValidator extends AbstractQuestionValidator {

    @Override
    public boolean validate(QuestionDto question) {
        return question.getOptions() != null && !question.getOptions().isEmpty()
                && question.getOptions().size() > 1;
    }

    @Override
    public boolean validateAnswer(AnswerDto answer, Question question) {
        if (answer.getSelectedOptions() == null || answer.getSelectedOptions().isEmpty()) {
            return false;
        }
        if (answer.getSelectedOptions().size() != 1) {
            return false;
        }
        if (question.getOptions() == null || question.getOptions().isEmpty()) {
            return false;
        }

        Set<Integer> optionIds = question.getOptions().stream()
                .map(Option::getId)
                .collect(Collectors.toSet());

        Integer selectedOptionId = answer.getSelectedOptions().get(0).getId();
        return optionIds.contains(selectedOptionId);
    }
}
