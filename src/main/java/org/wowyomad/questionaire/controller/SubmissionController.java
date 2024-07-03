package org.wowyomad.questionaire.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.SubmissionDto;
import org.wowyomad.questionaire.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@AllArgsConstructor
@CrossOrigin

public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping("")
    public List<SubmissionDto> getAllSubmissions(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        if(offset == null || limit == null) {
            return submissionService.getAllSubmissions();
        } else {
            return submissionService.getAllSubmissions(offset, limit);
        }
    }

    @GetMapping("/count")
    public Long getSubmissionsCount() {
        System.out.println("count method called");
        return submissionService.getCount();
    }

    @GetMapping("/{id}")
    public SubmissionDto getSubmission(@PathVariable("id") int id) {
        return submissionService.getSubmission(id);
    }

    @PostMapping("")
    public ResponseEntity<SubmissionDto> createSubmission(@RequestBody @Valid SubmissionDto submissionDto) {
         return new ResponseEntity<>(submissionService.saveSubmission(submissionDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmission(@PathVariable("id") int id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.noContent().build();
    }
}

