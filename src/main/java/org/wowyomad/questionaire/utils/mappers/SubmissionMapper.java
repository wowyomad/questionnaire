package org.wowyomad.questionaire.utils.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubmissionMapper {

    private final AnswerMapper answerMapper;


    public SubmissionDto mapToDto(Submission submission) {
        return new SubmissionDto(
                submission.getId(),
                submission.getAnswers().stream()
                        .map(answerMapper::mapToDto)
                        .toList()
        );
    }

    public Submission mapToEntity(SubmissionDto submissionDto, List<Question> questions) {
        Submission submission = new Submission();
        Iterator<Question> questionIterator = questions.iterator();
        submission.setId(submissionDto.getId());
        submission.setAnswers(submissionDto.getAnswers().stream()
                .map(answerDto -> answerMapper.mapToEntity(answerDto, questionIterator.next(), submission))
                .collect(Collectors.toList()));

        return submission;
    }

}
