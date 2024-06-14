package org.wowyomad.questionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.SubmissionCreateDTO;
import org.wowyomad.questionaire.dto.SubmissionResponseDTO;
import org.wowyomad.questionaire.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public List<SubmissionResponseDTO> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    @GetMapping("/{id}")
    public SubmissionResponseDTO getSubmissionById(@PathVariable Long id) {
        return submissionService.getSubmissionById(id);
    }

    @PostMapping
    public ResponseEntity<String> createSubmission(@RequestBody SubmissionCreateDTO submissionCreateDTO) {
        try {
            submissionService.saveSubmission(submissionCreateDTO);
            return ResponseEntity.ok("Successfully created submission");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubmission(@PathVariable Long id, @RequestBody SubmissionCreateDTO submissionCreateDTO) {
        try {
            submissionService.updateSubmission(id, submissionCreateDTO);
            return ResponseEntity.ok("Successfully updated submission");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubmission(@PathVariable Long id) {
        try {
            submissionService.deleteSubmission(id);
            return ResponseEntity.ok("Successfully deleted submission");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
