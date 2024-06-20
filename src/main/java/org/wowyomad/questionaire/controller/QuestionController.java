package org.wowyomad.questionaire.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.dto.QuestionListDto;
import org.wowyomad.questionaire.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("")
    List<QuestionDto> getAllQuestions() {
        return questionService.getAllQuestions();
    }


    @PostMapping("")
    boolean createQuestion(@RequestBody QuestionDto questionDto) {
        try {
            questionService.saveQuestion(questionDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @PutMapping("/{id}")
    boolean updateQuestion(@PathVariable("id") int id, @RequestBody QuestionDto questionDto) {
        try {
            questionService.updateQuestion(id, questionDto);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @GetMapping("/{id}")
    QuestionDto getQuestion(@PathVariable("id") int id) {
        QuestionDto questionDto;
        try {
            questionDto = questionService.getQuestion(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
        return questionDto;

    }

    @DeleteMapping("/{id}")
    boolean deleteQuestion(@PathVariable int id) {
        try {
            questionService.deleteQuestion(id);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @PostMapping("/multiple")
    public boolean createMultipleSubmissions(@RequestBody QuestionListDto questionListDto) {
        try {
            questionListDto.getQuestions().forEach(questionService::saveQuestion);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}

