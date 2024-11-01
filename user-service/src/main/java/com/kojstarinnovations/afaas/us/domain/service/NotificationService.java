package com.kojstarinnovations.afaas.us.domain.service;

import com.kojstarinnovations.afaas.commons.data.response.GenericMessage;
import com.kojstarinnovations.afaas.us.application.uc.WebSocketFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class NotificationService {

    private final WebSocketFeignClient webSocketFeignClient;

    @Autowired
    public NotificationService(WebSocketFeignClient webSocketFeignClient) {
        this.webSocketFeignClient = webSocketFeignClient;
    }

    /**
     * Notify to the frontend that the data has changed
     *
     * @param content the message to be sent
     */
    public <T> void notifyFrontend(String topic, T content) {
        GenericMessage<T> genericMessage = new GenericMessage<>();
        genericMessage.setDestination(String.format("/topic/%s", topic));
        genericMessage.setContent(content);
        try {
            webSocketFeignClient.sendNotification(genericMessage);
        } catch (Exception ex) {
            Logger.getLogger("NotificationService").info("Error: " + ex.getMessage());
        }
    }
}