package com.emendes.barms.controller;

import com.emendes.barms.dto.request.BarRequest;
import com.emendes.barms.dto.response.BarResponse;
import com.emendes.barms.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bars")
public class BarController {

  private final BarService barService;

  @PostMapping
  public ResponseEntity<BarResponse> create(@RequestBody BarRequest barRequest, UriComponentsBuilder uriBuilder) {
    BarResponse barResponse = barService.create(barRequest);

    URI uri = uriBuilder.path("/api/bars/{id}").build(barResponse.getId());

    return ResponseEntity.created(uri).body(barResponse);
  }

  @GetMapping
  public ResponseEntity<Page<BarResponse>> fetchAll(Pageable pageable) {
    return ResponseEntity.ok(barService.fetchAll(pageable));
  }

}
