package org.wowyomad.questionaire.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.repository.SubmissionRepository;
import org.wowyomad.questionaire.service.SubmissionService;
import org.wowyomad.questionaire.utils.mappers.SubmissionMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final QuestionRepository questionRepository;


    @Override
    public List<SubmissionDto> getAllSubmissions() {
        return submissionRepository.findAll().stream()
                .map(submissionMapper::mapToDto)
                .toList();
    }

    @Override
    public SubmissionDto getSubmission(int id) throws IllegalArgumentException {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Submission not found with id %d", id)));
        return submissionMapper.mapToDto(submission);
    }

    @Override
    public void saveSubmission(SubmissionDto submissionDto) throws IllegalArgumentException {
        List<Question> questions = submissionDto.getAnswers().stream()
                .map(answerDto -> questionRepository.findById(answerDto.getQuestionId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(String.format("Question in submission doesn't exist with id", answerDto.getQuestionId()))))
                .toList();
        Submission submission = submissionMapper.mapToEntity(submissionDto, questions);
        submissionRepository.save(submission);
    }

    @Override
    public void deleteSubmission(int id) throws IllegalArgumentException {
        submissionRepository.deleteById(id);
    }
}
