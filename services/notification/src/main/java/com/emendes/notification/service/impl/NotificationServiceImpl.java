package com.emendes.notification.service.impl;

import com.emendes.notification.dto.event.CreateBarEvent;
import com.emendes.notification.dto.response.NotificationResponse;
import com.emendes.notification.model.entity.Notification;
import com.emendes.notification.repository.NotificationRepository;
import com.emendes.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

  @Override
  public Page<NotificationResponse> fetchAll(Pageable pageable) {
    Page<Notification> notificationPage = notificationRepository.findAll(pageable);

    return notificationPage.map(this::notificationToNotificationResponse);
  }

  @Override
  public void save(CreateBarEvent createBarEvent) {
    Notification notification = Notification.builder()
        .message(String.format("Bar with id %d was created", createBarEvent.getId()))
        .date(LocalDateTime.now())
        .createdAt(LocalDateTime.now())
        .build();

    notificationRepository.save(notification);

    log.info("Notification with id {} saved successfully", notification.getId());
    // TODO: Talvez enviar a notificação via email ou sms.
  }

  private NotificationResponse notificationToNotificationResponse(Notification notification) {
    return NotificationResponse.builder()
        .id(notification.getId())
        .message(notification.getMessage())
        .date(notification.getDate())
        .build();
  }

}
