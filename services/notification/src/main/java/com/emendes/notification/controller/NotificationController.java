package com.emendes.notification.controller;

import com.emendes.notification.dto.NotificationResponse;
import com.emendes.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

  private final NotificationService notificationService;

  @GetMapping
  public Page<NotificationResponse> fetchAll(Pageable pageable) {
    return notificationService.fetchAll(pageable);
  }

}
