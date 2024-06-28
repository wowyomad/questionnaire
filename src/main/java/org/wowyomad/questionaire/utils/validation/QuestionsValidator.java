package org.wowyomad.questionaire.utils.validation;

import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.utils.enums.QuestionType;

import java.util.Map;

@Component
public class QuestionsValidator extends AbstractQuestionValidator {
    private final Map<QuestionType, AbstractQuestionValidator> validators;

    public QuestionsValidator() {
        validators = Map.ofEntries(
                Map.entry(QuestionType.SINGLE_LINE_TEXT, new TextQuestionValidator()),
                Map.entry(QuestionType.MULTILINE_TEXT, new TextQuestionValidator()),
                Map.entry(QuestionType.DATE, new DateQuestionValidator()),
                Map.entry(QuestionType.CHECKBOX, new MultiChoiceQuestionValidator()),
                Map.entry(QuestionType.RADIO_BUTTON, new SingleChoiceQuestionValidator()),
                Map.entry(QuestionType.COMBOBOX, new SingleChoiceQuestionValidator())
        );
    }

    @Override
    public boolean validate(QuestionDto question) {
        QuestionType type = QuestionType.valueOf(question.getType());
        return validators.get(type).validate(question);
    }

    @Override
    public boolean validateAnswer(AnswerDto answer, Question question) {
        return validators.get(question.getType()).validateAnswer(answer, question);

    }
}
