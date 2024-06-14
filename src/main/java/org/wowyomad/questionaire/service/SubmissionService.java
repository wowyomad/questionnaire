package org.wowyomad.questionaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.SubmissionCreateDTO;
import org.wowyomad.questionaire.dto.SubmissionResponseDTO;
import org.wowyomad.questionaire.map.SubmissionMapper;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.repository.SubmissionRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubmissionMapper submissionMapper;

    public void saveSubmission(SubmissionCreateDTO submissionCreateDTO) {
        Submission submission = submissionMapper.fromCreateDTO(submissionCreateDTO);

        for (Answer answer : submission.getAnswers()) {
            Question question = questionRepository.findById(answer.getQuestion().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + answer.getQuestion().getId()));
            answer.setQuestion(question);
            answer.setSubmission(submission);
            validateAnswer(answer, question);
            setAnswerOptions(answer, question, answer.getSelectedOptions());
        }

        submissionRepository.save(submission);
    }

    public List<SubmissionResponseDTO> getAllSubmissions() {
        return submissionRepository.findAll().stream()
                .map(submissionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SubmissionResponseDTO getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .map(submissionMapper::toResponseDTO)
                .orElse(null);
    }

    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }

    public SubmissionResponseDTO updateSubmission(Long id, SubmissionCreateDTO submissionCreateDTO) {
        if (!submissionRepository.existsById(id)) {
            throw new IllegalArgumentException("Submission not found");
        }

        Submission submission = submissionMapper.fromCreateDTO(submissionCreateDTO);
        submission.setId(id);

        for (Answer answer : submission.getAnswers()) {
            Question question = questionRepository.findById(answer.getQuestion().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + answer.getQuestion().getId()));
            answer.setQuestion(question);
            answer.setSubmission(submission);
            validateAnswer(answer, question);
            setAnswerOptions(answer, question, answer.getSelectedOptions());
        }

        Submission updatedSubmission = submissionRepository.save(submission);
        return submissionMapper.toResponseDTO(updatedSubmission);
    }

    private void setAnswerOptions(Answer answer, Question question, List<Long> selectedOptionIndices) {
        if (selectedOptionIndices == null) {
            return;
        }
        List<Option> selectedOptions = question.getOptions().stream()
                .filter(option -> selectedOptionIndices.contains(option.getIndex()))
                .collect(Collectors.toList());
        if (selectedOptions.size() != selectedOptionIndices.size()) {
            throw new IllegalArgumentException("Invalid option indices provided");
        }
        answer.setSelectedOptions(selectedOptions.stream().map(Option::getId).collect(Collectors.toList()));
    }

    private void validateAnswer(Answer answer, Question question) {
        switch (question.getType()) {
            case SINGLE_LINE_TEXT:
            case MULTILINE_TEXT:
                if (answer.getResponse() == null || answer.getResponse().isBlank()) {
                    throw new IllegalArgumentException("Response cannot be empty for text fields");
                }
                break;
            case DATE:
                try {
                    LocalDate.parse(answer.getResponse());
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format");
                }
                break;
            case RADIO_BUTTON:
            case COMBOBOX:
                if (answer.getSelectedOptions() == null || answer.getSelectedOptions().size() != 1) {
                    throw new IllegalArgumentException("Invalid option selected");
                }
                Long optionIndex = answer.getSelectedOptions().get(0);
                question.getOptions().stream()
                        .filter(opt -> opt.getIndex().equals(optionIndex))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Invalid option index: " + optionIndex));
                break;
            case CHECKBOX:
                if (answer.getSelectedOptions() == null || answer.getSelectedOptions().isEmpty()) {
                    throw new IllegalArgumentException("Invalid options selected");
                }
                List<Long> optionIndices = answer.getSelectedOptions();
                List<Option> selectedOptions = question.getOptions().stream()
                        .filter(opt -> optionIndices.contains(opt.getIndex()))
                        .collect(Collectors.toList());
                if (selectedOptions.size() != optionIndices.size()) {
                    throw new IllegalArgumentException(String.format("Invalid options size. Selected: %d, Size: %d", selectedOptions.size(), optionIndices.size()));
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown question type");
        }
    }
}
