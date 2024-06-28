package org.wowyomad.questionaire.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@AllArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;
    @GetMapping("")
    List<SubmissionDto> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    SubmissionDto getSubmission(@PathVariable("id") int id) {
        SubmissionDto submissionDto;
        try {
            submissionDto = submissionService.getSubmission(id);

        } catch (IllegalArgumentException e) {
            System.out.println(e);
            submissionDto = null;
        }
        return submissionDto;
    }

    @PostMapping("")
    boolean createSubmission(@RequestBody @Valid SubmissionDto submissionDto) {
        try {
            submissionService.saveSubmission(submissionDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @DeleteMapping("/{id}")
    boolean deleteSubmission(@PathVariable("id") int id) {
        try {
            submissionService.deleteSubmission(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}
