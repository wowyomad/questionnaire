package org.wowyomad.questionaire.utils.events;

import org.springframework.context.ApplicationEvent;

public class SubmissionsUpdatedEvent extends ApplicationEvent {

    public SubmissionsUpdatedEvent(Object source) {
        super(source);
    }
}
