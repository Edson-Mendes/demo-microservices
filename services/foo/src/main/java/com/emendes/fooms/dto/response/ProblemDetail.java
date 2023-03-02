package com.emendes.fooms.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class ProblemDetail {

  private String title;
  private String detail;
  private int status;
  private LocalDateTime timestamp;

}
