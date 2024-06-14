package org.wowyomad.questionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.AnswerCreateDTO;
import org.wowyomad.questionaire.dto.AnswerResponseDTO;
import org.wowyomad.questionaire.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<AnswerResponseDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public AnswerResponseDTO getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    @PostMapping
    public ResponseEntity<String> createAnswer(@RequestBody AnswerCreateDTO answerCreateDTO) {
        try {
            answerService.saveAnswer(answerCreateDTO);
            return ResponseEntity.ok("Successfully created answer");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAnswer(@PathVariable Long id, @RequestBody AnswerCreateDTO answerCreateDTO) {
        try {
            answerService.saveAnswer(answerCreateDTO);
            return ResponseEntity.ok("Successfully updated answer");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Successfully deleted answer");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
