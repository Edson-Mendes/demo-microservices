package com.emendes.notification.service;

import com.emendes.notification.dto.event.CreateBarEvent;
import com.emendes.notification.dto.response.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

  Page<NotificationResponse> fetchAll(Pageable pageable);

  void save(CreateBarEvent createBarEvent);
}
