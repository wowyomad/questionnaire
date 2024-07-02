package org.wowyomad.questionaire.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wowyomad.questionaire.dto.AuthenticationRequest;
import org.wowyomad.questionaire.dto.AuthenticationResponse;
import org.wowyomad.questionaire.dto.RegisterRequest;
import org.wowyomad.questionaire.service.AuthenticationService;
import org.wowyomad.questionaire.service.MailService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authService;
    private final MailService mailService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        var response = ResponseEntity.ok(authService.register(request));

        String message = String.format("""
                        <html>
                        <head></head>
                        <body>
                            <h1>Successfully registered!</h1>
                            <h2>password: %s</h2>
                        </body>
                        </html>
                        """,
                request.getPassword()
        );
        mailService.sendNotifyingMail(request.getEmail(), message);

        return response;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        var response = ResponseEntity.ok(authService.authenticate(request));

        String message = "Successfully authorized!";
        mailService.sendNotifyingMail(request.getEmail(), message);

        return response;
    }
}
