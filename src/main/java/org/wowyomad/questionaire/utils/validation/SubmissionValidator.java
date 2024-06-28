package org.wowyomad.questionaire.utils.validation;

import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.model.Question;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class SubmissionValidator{
    private final QuestionsValidator questionsValidator;

    public SubmissionValidator(QuestionsValidator questionValidator) {
        this.questionsValidator = questionValidator;
    }

    public boolean validate(SubmissionDto submission, List<Question> questions) {
        if(questions == null || questions.isEmpty()) return false;

        List<AnswerDto> answers = submission.getAnswers();
        if(answers == null || answers.isEmpty()) {
            return false;
        }

        Map<Integer, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, question -> question));

        Set<Integer> requiredQuestionIds = questionMap.values().stream()
                .filter(Question::isRequired)
                .map(Question::getId)
                .collect(Collectors.toSet());

        Set<Integer> activeQuestionIds = questionMap.values().stream()
                .filter(Question::isActive)
                .map(Question::getId)
                .collect(Collectors.toSet());

        Set<Integer> answeredQuestionIds = answers.stream()
                .map(AnswerDto::getQuestionId)
                .collect(Collectors.toSet());
        System.out.println("Required: " + requiredQuestionIds);
        System.out.println("Active: " + activeQuestionIds);
        System.out.println("Answered: " + answeredQuestionIds);
        if (!answeredQuestionIds.containsAll(requiredQuestionIds)) {
            return false;
        }

        for (AnswerDto answer : answers) {
            Question question = questionMap.get(answer.getQuestionId());
            if (question == null || !activeQuestionIds.contains(answer.getQuestionId())) {
                return false;
            }
            if (!questionsValidator.validateAnswer(answer, question)) {
                return false;
            }
        }

        return true;

    }
}
