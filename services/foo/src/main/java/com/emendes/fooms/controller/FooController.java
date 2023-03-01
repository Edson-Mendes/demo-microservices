package com.emendes.fooms.controller;

import com.emendes.fooms.dto.request.FooRequest;
import com.emendes.fooms.dto.response.FooResponse;
import com.emendes.fooms.service.FooService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/foo")
public class FooController {

  private final FooService fooService;

  @PostMapping
  public ResponseEntity<FooResponse> create(@RequestBody FooRequest fooRequest, UriComponentsBuilder uriBuilder) {
    FooResponse foo = fooService.create(fooRequest);
    URI uri = uriBuilder.path("/api/foo/{id}").build(foo.getId());
    return ResponseEntity.created(uri).body(foo);
  }

  @GetMapping
  public ResponseEntity<Page<FooResponse>> fetchAll(Pageable pageable) {
    return ResponseEntity.ok(fooService.fetchAll(pageable));
  }


}
