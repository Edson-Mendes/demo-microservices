package com.emendes.notification.dto.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateBarEvent {

  private Long id;
  private String name;

}
