package org.wowyomad.questionaire.utils;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.RegisterRequest;
import org.wowyomad.questionaire.service.AuthenticationService;

@Component
@RequiredArgsConstructor
public class InitialUserSetup {


    private final AuthenticationService authenticationService;

    private final String firstName = "John";
    private final String lastName = "Doe";
    private final String email = "email@example.com";
    private final String password = "1111";
    private final String phone = "+375-666-77-88";

    @EventListener(ApplicationReadyEvent.class)
    void createInitialUser() {
        RegisterRequest initialUserRequest = new RegisterRequest(
                firstName, lastName, email, password, phone
        );
        String token = authenticationService.register(initialUserRequest).getToken();
        System.out.printf("""
                Test user
                email: %s;
                password: %s;
                token: %s
                """, email, password, token);

    }
}
