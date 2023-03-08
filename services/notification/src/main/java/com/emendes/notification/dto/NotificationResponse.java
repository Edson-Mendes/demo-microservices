package com.emendes.notification.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class NotificationResponse {

  private Long id;
  private String message;
  private LocalDateTime date;

}
