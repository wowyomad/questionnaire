package org.wowyomad.questionaire.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping()
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test");
    }
}
