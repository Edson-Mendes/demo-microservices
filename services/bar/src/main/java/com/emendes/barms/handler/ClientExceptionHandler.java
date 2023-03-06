package com.emendes.barms.handler;

import com.emendes.barms.dto.response.ProblemDetail;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.net.ConnectException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ClientExceptionHandler {

  @ExceptionHandler({CallNotPermittedException.class})
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ProblemDetail handleCallNotPermittedException() {
    return ProblemDetail.builder()
        .title("Something went wrong")
        .detail("Call not permitted, try again later!")
        .status(HttpStatus.SERVICE_UNAVAILABLE.value())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ExceptionHandler(ConnectException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ProblemDetail handleConnectionException() {
    return ProblemDetail.builder()
        .title("Something went wrong")
        .detail("Fail to connect to microservice foo")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ExceptionHandler(WebClientResponseException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ProblemDetail handleWebClientResponseException() {
    return ProblemDetail.builder()
        .title("Something went wrong")
        .detail("503 Service Unavailable from UNKNOWN")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .timestamp(LocalDateTime.now())
        .build();
  }


}
