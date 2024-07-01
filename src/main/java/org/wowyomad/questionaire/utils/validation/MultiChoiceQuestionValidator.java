package org.wowyomad.questionaire.utils.validation;

import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.OptionDto;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;

import java.util.Set;
import java.util.stream.Collectors;

class MultiChoiceQuestionValidator extends AbstractQuestionValidator {
    @Override
    public boolean validate(QuestionDto question) {
        return question.getOptions() != null && !question.getOptions().isEmpty();
    }

    @Override
    public boolean validateAnswer(AnswerDto answer, Question question) {
        if(answer.getSelectedOptions() == null) return false;
        if(question.getOptions() == null) return false;

        Set<Integer> optionIds = question.getOptions().stream()
                .map(Option::getId)
                .collect(Collectors.toSet());

        Set<Integer> selectedOptionIds = answer.getSelectedOptions().stream()
                .map(OptionDto::getId)
                .collect(Collectors.toSet());

        return optionIds.containsAll(selectedOptionIds);

    }
}
