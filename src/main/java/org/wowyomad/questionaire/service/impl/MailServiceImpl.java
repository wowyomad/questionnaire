package org.wowyomad.questionaire.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.service.MailService;
import org.wowyomad.questionaire.utils.exceptions.MessageWasNotSentException;

@Service
@Profile("prod")
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Async
    @Override
    public void sendNotifyingMail(String to, String text) throws MessageWasNotSentException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        final String subject = "Notification";

        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new MessageWasNotSentException("Failed to send email to " + to, e);
        }
    }
}
