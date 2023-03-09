package com.emendes.notification.listener;

import com.emendes.notification.dto.event.CreateBarEvent;
import com.emendes.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationListener {

  private final NotificationService notificationService;

  @KafkaListener(topics = "notificationTopic")
  public void handleNotification(CreateBarEvent createBarEvent) {
    log.info("notification received");

    notificationService.save(createBarEvent);
  }

}
