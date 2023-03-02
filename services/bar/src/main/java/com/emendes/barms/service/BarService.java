package com.emendes.barms.service;

import com.emendes.barms.dto.request.BarRequest;
import com.emendes.barms.dto.response.BarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BarService {

  BarResponse create(BarRequest barRequest);

  Page<BarResponse> fetchAll(Pageable pageable);
}
