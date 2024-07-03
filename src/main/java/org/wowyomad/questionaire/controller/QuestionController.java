package org.wowyomad.questionaire.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.dto.QuestionListDto;
import org.wowyomad.questionaire.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
@CrossOrigin

public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("")
    public List<QuestionDto> getAllQuestions(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit) {
        if (offset == null || limit == null) {
            return questionService.getAllQuestions();
        }
        return questionService.getAllQuestions(offset, limit);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getQuestionsCount() {
        return new ResponseEntity<>(questionService.countQuestions(), HttpStatus.OK) ;
    }

    @PostMapping("")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody @Valid QuestionDto questionDto) {
        QuestionDto createQuestion = questionService.saveQuestion(questionDto);
        return new ResponseEntity<>(createQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable("id") int id, @RequestBody @Valid QuestionDto questionDto) {
        QuestionDto updatedQuestion = questionService.updateQuestion(id, questionDto);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestion(@PathVariable("id") int id) {
        return questionService.getQuestion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/multiple")
    public ResponseEntity<String> createMultipleQuestions(@RequestBody @Valid QuestionListDto questionListDto) {
        questionService.saveAllQuestions(questionListDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

