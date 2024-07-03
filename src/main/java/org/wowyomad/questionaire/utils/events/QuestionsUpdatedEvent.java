package org.wowyomad.questionaire.utils.events;

import org.springframework.context.ApplicationEvent;

public class QuestionsUpdatedEvent extends ApplicationEvent {
    public QuestionsUpdatedEvent(Object source) {
        super(source);
    }
}
