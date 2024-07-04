package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.repository.SubmissionRepository;
import org.wowyomad.questionaire.service.SubmissionService;
import org.wowyomad.questionaire.utils.events.SubmissionsUpdatedEvent;
import org.wowyomad.questionaire.utils.exceptions.SubmissionInvalidException;
import org.wowyomad.questionaire.utils.exceptions.SubmissionNotFoundException;
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
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<SubmissionDto> getAllSubmissions() {
        return submissionRepository.findAll().stream()
                .map(submissionMapper::mapToDto)
                .toList();
    }

    @Override
    public List<SubmissionDto> getAllSubmissions(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return submissionRepository.findAll(pageable).stream()
                .map(submissionMapper::mapToDto)
                .toList();

    }

    @Override
    public Long getCount() {
        return submissionRepository.count();
    }

    @Override
    public SubmissionDto getSubmission(int id) throws SubmissionNotFoundException {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new SubmissionNotFoundException(String.format("Submission not found with id %d", id)));
        return submissionMapper.mapToDto(submission);
    }

    @Override
    public SubmissionDto saveSubmission(SubmissionDto submissionDto) throws SubmissionInvalidException {
        if(submissionDto.getAnswers() == null || submissionDto.getAnswers().isEmpty()) {
            throw new SubmissionInvalidException("Submission doesn't contain answers");
        }
        List<Question> questions = questionRepository.findAll();
        Map<Integer, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, question -> question));

        if(!submissionValidator.validate(submissionDto, questions)) {
            throw new SubmissionInvalidException("Submission is not valid");
        }

        Submission submission = submissionMapper.mapToEntity(submissionDto, questionMap);
        submission = submissionRepository.save(submission);

        eventPublisher.publishEvent(new SubmissionsUpdatedEvent(this));

        return submissionMapper.mapToDto(submission);

    }

    @Override
    public void deleteSubmission(int id) throws SubmissionNotFoundException {
        submissionRepository.deleteById(id);
        eventPublisher.publishEvent(new SubmissionsUpdatedEvent(this));
    }

}
