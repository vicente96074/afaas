package com.kojstarinnovations.afaas.commons.ports.output.event;


import com.kojstarinnovations.afaas.commons.ports.output.Event;

import java.time.LocalDateTime;

/**
 * This class represents a deleted event
 *
 * @author balamkiche
 */
public class DeletionEvent<USER, COMPONENT, PROCESS, TRANSACTION_ID, CRITERIA> extends Event<USER, COMPONENT, PROCESS, TRANSACTION_ID, CRITERIA> {

    public DeletionEvent(String message, LocalDateTime date, USER user,COMPONENT component, PROCESS process, TRANSACTION_ID transactionId, CRITERIA criteria) {
        super(message, date, user, component, process, transactionId, criteria);
    }
}
