package com.emendes.fooms.controller;

import com.emendes.fooms.dto.request.FooRequest;
import com.emendes.fooms.dto.response.FooResponse;
import com.emendes.fooms.service.FooService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/foos")
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

  @GetMapping("/{id}")
  public ResponseEntity<FooResponse> findById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(fooService.findById(id));
  }


}
