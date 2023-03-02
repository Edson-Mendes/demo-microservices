package com.emendes.fooms.service.impl;

import com.emendes.fooms.dto.request.FooRequest;
import com.emendes.fooms.dto.response.FooResponse;
import com.emendes.fooms.model.entity.Foo;
import com.emendes.fooms.repository.FooRepository;
import com.emendes.fooms.service.FooService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class FooServiceImpl implements FooService {

  private final FooRepository fooRepository;

  @Override
  public FooResponse create(FooRequest fooRequest) {
    Foo foo = fooRepository.save(fooRequestToFoo(fooRequest));

    log.info("foo {} saved successfully", foo.getId());

    return fooToFooResponse(foo);
  }

  @Override
  public Page<FooResponse> fetchAll(Pageable pageable) {
    Page<Foo> fooPage = fooRepository.findAll(pageable);

    log.info("fetch pageable foo");

    return fooPage.map(this::fooToFooResponse);
  }

  @Override
  public FooResponse findById(long id) {
    Foo foo = fooRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Foo not found"));

    return fooToFooResponse(foo);
  }

  private FooResponse fooToFooResponse(Foo foo) {
    return FooResponse.builder()
        .id(foo.getId())
        .name(foo.getName())
        .build();
  }

  private Foo fooRequestToFoo(FooRequest fooRequest) {
    return Foo.builder()
        .name(fooRequest.getName())
        .createdAt(LocalDateTime.now())
        .build();
  }

}
