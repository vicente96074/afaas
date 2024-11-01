package com.kojstarinnovations.afaas.us.domain.eventlistener;

import com.kojstarinnovations.afaas.commons.ports.output.Event;
import com.kojstarinnovations.afaas.commons.ports.output.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * EventListenerAdapter
 *
 * @author Augusto Vicente
 */
@Component
@RequiredArgsConstructor
public class EventListenerAdapter implements EventPublisher {

    /**
     * Handle
     *
     * @param event of type EVENT
     */
    @EventListener
    public void handle(Event event) {
        String message = String.format("[%s] [%s] [TransactionID: %s] %s: %s AT %s by %s", event.getComponent(), event.getProcess(), event.getTransactionId(), event.getMessage(), event.getCriteria(), event.getDate(), event.getUser());
        Logger.getLogger("EventListenerAdapter").info(message);
        applicationEventPublisher.publishEvent(message);
    }

    private final ApplicationEventPublisher applicationEventPublisher;
}
