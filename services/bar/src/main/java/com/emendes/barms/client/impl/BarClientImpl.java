package com.emendes.barms.client.impl;

import com.emendes.barms.client.BarClient;
import com.emendes.barms.dto.response.FooResponse;
import com.emendes.barms.dto.response.ProblemDetail;
import com.emendes.barms.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Component
public class BarClientImpl implements BarClient {

  private final WebClient.Builder webClientBuilder;
  private final String PATH = "http://foo-service/api/foos/";

  @Override
  public FooResponse fetchFooById(Long fooId) {
    return webClientBuilder.build()
        .get().uri(PATH + fooId)
        .header("Content-Type", "application/json")
        .retrieve()
        .onStatus(HttpStatus::is4xxClientError, cr -> cr
            .bodyToMono(ProblemDetail.class).map(p -> new EntityNotFoundException(
                "Invalid fooId",
                "Foo not found with id " + fooId,
                HttpStatus.BAD_REQUEST)))
        .bodyToMono(FooResponse.class).block();
  }

}
