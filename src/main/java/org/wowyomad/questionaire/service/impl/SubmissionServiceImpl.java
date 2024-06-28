package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.repository.SubmissionRepository;
import org.wowyomad.questionaire.service.SubmissionService;
import org.wowyomad.questionaire.utils.mappers.SubmissionMapper;
import org.wowyomad.questionaire.utils.validation.SubmissionValidator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final QuestionRepository questionRepository;
    private final SubmissionValidator submissionValidator;


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
        if(submissionDto.getAnswers() == null || submissionDto.getAnswers().isEmpty()) {
            throw new IllegalArgumentException("Submission doesn't contain answers");
        }
        List<Question> questions = questionRepository.findAll();
        Map<Integer, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, question -> question));

        if(!submissionValidator.validate(submissionDto, questions)) {
            throw new IllegalArgumentException("Submission is not valid");
        }

        Submission submission = submissionMapper.mapToEntity(submissionDto, questionMap);
        submissionRepository.save(submission);
    }

    @Override
    public void deleteSubmission(int id) throws IllegalArgumentException {
        submissionRepository.deleteById(id);
    }
}
