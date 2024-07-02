package org.wowyomad.questionaire.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.service.MailService;
import org.wowyomad.questionaire.utils.exceptions.MessageWasNotSentException;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class MailServiceDevImpl implements MailService {

    @Async
    @Override
    public void sendNotifyingMail(String to, String text) throws MessageWasNotSentException{
        System.out.printf("sendNotifyingMail(%s, %s)%n", to, text);
    }
}

