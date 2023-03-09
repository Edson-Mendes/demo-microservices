package com.emendes.barms.service.impl;

import com.emendes.barms.client.BarClient;
import com.emendes.barms.dto.request.BarRequest;
import com.emendes.barms.dto.response.BarResponse;
import com.emendes.barms.dto.event.CreateBarEvent;
import com.emendes.barms.model.entity.Bar;
import com.emendes.barms.repository.BarRepository;
import com.emendes.barms.service.BarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BarServiceImpl implements BarService {

  private final BarRepository barRepository;
  private final BarClient barClient;

  private final KafkaTemplate<String, CreateBarEvent> kafkaTemplate;

  @Override
  public BarResponse create(BarRequest barRequest) {

    barClient.fetchFooById(barRequest.getFooId());
    Bar bar = barRepository.save(barRequestToBar(barRequest));

    CreateBarEvent createBarEvent = CreateBarEvent.builder()
        .id(bar.getId())
        .name(bar.getName())
        .build();

    kafkaTemplate.send("notificationTopic", createBarEvent);

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
