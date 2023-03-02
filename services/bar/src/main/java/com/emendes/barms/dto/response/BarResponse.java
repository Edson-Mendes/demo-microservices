package com.emendes.barms.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class BarResponse {

  private Long id;
  private String name;
  private Long fooId;

}
