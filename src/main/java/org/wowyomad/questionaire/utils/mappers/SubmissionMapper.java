package org.wowyomad.questionaire.utils.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;
import org.wowyomad.questionaire.utils.Values;
import org.wowyomad.questionaire.utils.exceptions.QuestionNotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubmissionMapper {

    private final AnswerMapper answerMapper;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Values.dateTimePattern);

    public SubmissionDto mapToDto(Submission submission) {
        return new SubmissionDto(
                submission.getId(),
                submission.getSubmissionTime().format(dateTimeFormatter),
                submission.getAnswers().stream()
                        .map(answerMapper::mapToDto)
                        .toList()
        );
    }

    public Submission mapToEntity(SubmissionDto submissionDto, Map<Integer, Question> questions) throws QuestionNotFoundException {
        Submission submission = new Submission();
        submission.setId(submissionDto.getId());
        submission.setAnswers(submissionDto.getAnswers().stream()
                .map(answerDto -> {
                    Question question = questions.get(answerDto.getQuestionId());
                    if (question == null) {
                        throw new QuestionNotFoundException("Question not found for id: " + answerDto.getQuestionId());
                    }
                    return answerMapper.mapToEntity(answerDto, question, submission);
                })
                .collect(Collectors.toList()));

        return submission;
    }
}
