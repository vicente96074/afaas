package com.kojstarinnovations.afaas.us.application.uc;

import com.kojstarinnovations.afaas.commons.data.response.GenericMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "websocket-service")
public interface WebSocketFeignClient {

    @PostMapping("/api/websocket/users")
    <T> void sendNotification(@RequestBody GenericMessage<T> message);
}