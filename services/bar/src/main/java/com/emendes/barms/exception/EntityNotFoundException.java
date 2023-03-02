package com.emendes.barms.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends RuntimeException {

  private String title;
  private HttpStatus status;

  public EntityNotFoundException(String message) {
    this("Entity not found", message, HttpStatus.NOT_FOUND);
  }

  public EntityNotFoundException(String title, String message, HttpStatus status) {
    super(message);
    this.title = title;
    this.status = status;
  }

}
