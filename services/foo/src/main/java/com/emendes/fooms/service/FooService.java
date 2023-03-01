package com.emendes.fooms.service;

import com.emendes.fooms.dto.request.FooRequest;
import com.emendes.fooms.dto.response.FooResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FooService {

  FooResponse create(FooRequest fooRequest);

  Page<FooResponse> fetchAll(Pageable pageable);

}
