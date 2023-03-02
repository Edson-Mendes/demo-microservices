package com.emendes.fooms.handler;

import com.emendes.fooms.dto.response.ProblemDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class EntityNotFoundHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleEntityNotFound(EntityNotFoundException exception) {
    ProblemDetail problem = ProblemDetail.builder()
        .title("Entity not found")
        .detail(exception.getMessage())
        .status(HttpStatus.NOT_FOUND.value())
        .timestamp(LocalDateTime.now())
        .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
  }

}
