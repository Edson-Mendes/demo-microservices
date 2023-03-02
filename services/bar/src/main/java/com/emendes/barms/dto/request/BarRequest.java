package com.emendes.barms.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class BarRequest {

  private String name;
  private Long fooId;

}
