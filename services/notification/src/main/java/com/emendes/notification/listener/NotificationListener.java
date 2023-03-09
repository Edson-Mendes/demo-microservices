package com.emendes.notification.listener;

import com.emendes.notification.dto.event.CreateBarEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationListener {

  @KafkaListener(topics = "notificationTopic")
  public void handleNotification(CreateBarEvent createBarEvent) {
    log.info("The bar {} was created with id {}", createBarEvent.getName(), createBarEvent.getId());
    log.info("Preparing to send notifications");
    log.info("Sending notification by SMS");
    log.info("Sending notification by E-mail");
    log.info("Send messages done");
  }

}
