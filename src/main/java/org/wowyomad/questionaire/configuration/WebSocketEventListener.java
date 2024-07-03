package org.wowyomad.questionaire.configuration;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.utils.events.QuestionsUpdatedEvent;
import org.wowyomad.questionaire.utils.events.SubmissionsUpdatedEvent;

@Component
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleQuestionsUpdated(QuestionsUpdatedEvent event) {
        System.out.println("QuestionUpdatedEvent broadcast!!");
        messagingTemplate.convertAndSend("/topic/questionsUpdated", "Questions updated");
    }

    @EventListener
    public void handleSubmissionsUpdated(SubmissionsUpdatedEvent event) {
        messagingTemplate.convertAndSend("/topic/submissionsUpdated", "Submissions updated");
    }
}
