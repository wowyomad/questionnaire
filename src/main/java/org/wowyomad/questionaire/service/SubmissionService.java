package org.wowyomad.questionaire.service;

import org.wowyomad.questionaire.dto.SubmissionDto;

import java.util.List;

public interface SubmissionService {
    List<SubmissionDto> getAllSubmissions();
    SubmissionDto getSubmission(int id) throws IllegalArgumentException;
    SubmissionDto saveSubmission(SubmissionDto submissionDto) throws IllegalArgumentException;
    void deleteSubmission(int id) throws IllegalArgumentException;

    List<SubmissionDto> getAllSubmissions(Integer offset, Integer limit);

    Long getCount();
}
