package com.emendes.fooms.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class FooResponse {

  private Long id;
  private String name;

}
