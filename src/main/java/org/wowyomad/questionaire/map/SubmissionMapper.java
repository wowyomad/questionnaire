package org.wowyomad.questionaire.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.SubmissionCreateDTO;
import org.wowyomad.questionaire.dto.SubmissionResponseDTO;
import org.wowyomad.questionaire.model.Submission;

import java.util.stream.Collectors;

@Component
public class SubmissionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    public SubmissionResponseDTO toResponseDTO(Submission submission) {
        if (submission == null) {
            return null;
        }

        SubmissionResponseDTO dto = new SubmissionResponseDTO();
        dto.setId(submission.getId());
        dto.setSubmissionTime(submission.getSubmissionTime());
        dto.setAnswers(submission.getAnswers().stream()
                .map(answerMapper::toResponseDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    public Submission fromCreateDTO(SubmissionCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Submission submission = new Submission();
        submission.setSubmissionTime(dto.getSubmissionTime());
        submission.setAnswers(dto.getAnswers().stream()
                .map(answerMapper::fromCreateDTO)
                .collect(Collectors.toList()));

        return submission;
    }
}
