package com.emendes.barms.handler;

import com.emendes.barms.dto.response.ProblemDetail;
import com.emendes.barms.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EntityNotFoundHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleEntityNotFound(EntityNotFoundException exception) {
    ProblemDetail problem = ProblemDetail.builder()
        .title(exception.getTitle())
        .detail(exception.getMessage())
        .status(exception.getStatus().value())
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity.status(exception.getStatus()).body(problem);
  }

}
