package com.emendes.barms.service.impl;

import com.emendes.barms.dto.request.BarRequest;
import com.emendes.barms.dto.response.BarResponse;
import com.emendes.barms.model.entity.Bar;
import com.emendes.barms.repository.BarRepository;
import com.emendes.barms.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BarServiceImpl implements BarService {

  private final BarRepository barRepository;

  @Override
  public BarResponse create(BarRequest barRequest) {

    // TODO: Realizar uma requisição para o serviço foo e verificar se existe foo com o dado id.
//    if (client.existsFoo(barRequest.getFooId()))

    Bar bar = barRepository.save(barRequestToBar(barRequest));

    return barToBarResponse(bar);
  }

  @Override
  public Page<BarResponse> fetchAll(Pageable pageable) {
    Page<Bar> barPage = barRepository.findAll(pageable);

    return barPage.map(this::barToBarResponse);
  }

  private BarResponse barToBarResponse(Bar bar) {
    return BarResponse.builder()
        .id(bar.getId())
        .name(bar.getName())
        .fooId(bar.getFooId())
        .build();
  }

  private Bar barRequestToBar(BarRequest barRequest) {
    return Bar.builder()
        .name(barRequest.getName())
        .fooId(barRequest.getFooId())
        .createdAt(LocalDateTime.now())
        .build();
  }

}
