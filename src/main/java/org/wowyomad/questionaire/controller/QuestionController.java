package org.wowyomad.questionaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.QuestionCreateDTO;
import org.wowyomad.questionaire.dto.QuestionResponseDTO;
import org.wowyomad.questionaire.map.QuestionMapper;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping
    public List<QuestionResponseDTO> getAllQuestions() {
        return questionService.getAllQuestions().stream()
                .map(questionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuestionResponseDTO getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return question != null ? questionMapper.toResponseDTO(question) : null;
    }

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionCreateDTO questionCreateDTO) {
        try {
            Question question = questionMapper.fromCreateDTO(questionCreateDTO);
            questionService.saveQuestion(question);
            return ResponseEntity.ok("Successfully created question");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Long id, @RequestBody QuestionCreateDTO questionCreateDTO) {
        try {
            Question question = questionMapper.fromCreateDTO(questionCreateDTO);
            question.setId(id);
            questionService.saveQuestion(question);
            return ResponseEntity.ok("Successfully updated question");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.ok("Successfully deleted question");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
