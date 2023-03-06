package com.emendes.barms.controller;

import com.emendes.barms.dto.request.BarRequest;
import com.emendes.barms.dto.response.BarResponse;
import com.emendes.barms.dto.response.ProblemDetail;
import com.emendes.barms.service.BarService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bars")
public class BarController {

  private final BarService barService;

  @PostMapping
  @CircuitBreaker(name = "fooService")
//  @Retry(name = "retryApi", fallbackMethod = "fallbackAfterRetry")
  public ResponseEntity<BarResponse> create(@RequestBody BarRequest barRequest, UriComponentsBuilder uriBuilder) {
    BarResponse barResponse = barService.create(barRequest);

    URI uri = uriBuilder.path("/api/bars/{id}").build(barResponse.getId());

    return ResponseEntity.created(uri).body(barResponse);
  }

  @GetMapping
  public ResponseEntity<Page<BarResponse>> fetchAll(Pageable pageable) {
    return ResponseEntity.ok(barService.fetchAll(pageable));
  }

  public ResponseEntity<ProblemDetail> fallBackMethod(
      BarRequest barRequest, UriComponentsBuilder uriBuilder, RuntimeException exception) {

    ProblemDetail problem = ProblemDetail.builder()
        .title("Unavailable service")
        .detail("Foo service is unavailable, try again later!")
        .status(400)
        .timestamp(LocalDateTime.now())
        .build();

    log.error("Erro na requisição para foo service ::: message is {}", exception.getMessage());

    return ResponseEntity.badRequest().body(problem);
  }

  public ResponseEntity<String> fallbackAfterRetry(Exception ex) {
    return ResponseEntity.internalServerError().body("all retries have exhausted");
  }

}
