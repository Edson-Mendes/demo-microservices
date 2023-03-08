package com.emendes.notification.service.impl;

import com.emendes.notification.dto.NotificationResponse;
import com.emendes.notification.model.entity.Notification;
import com.emendes.notification.repository.NotificationRepository;
import com.emendes.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

  private final NotificationRepository notificationRepository;

  @Override
  public Page<NotificationResponse> fetchAll(Pageable pageable) {
    Page<Notification> notificationPage = notificationRepository.findAll(pageable);

    return notificationPage.map(this::notificationToNotificationResponse);
  }

  private NotificationResponse notificationToNotificationResponse(Notification notification) {
    return NotificationResponse.builder()
        .id(notification.getId())
        .message(notification.getMessage())
        .date(notification.getDate())
        .build();
  }

}
