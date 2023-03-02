package com.emendes.barms.client;

import com.emendes.barms.dto.response.FooResponse;

public interface BarClient {

  FooResponse fetchFooById(Long fooId);

}
