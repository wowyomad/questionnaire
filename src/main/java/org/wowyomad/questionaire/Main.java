package org.wowyomad.questionaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.wowyomad.questionaire.utils.exceptions.ApplicationSpecificException;
import org.wowyomad.questionaire.utils.exceptions.UserNotFoundException;

@SpringBootApplication
public class Main {
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

    }
    public static void main(String[] args) {

        System.out.println("Started application");

        SpringApplication.run(Main.class, args);

    }
}