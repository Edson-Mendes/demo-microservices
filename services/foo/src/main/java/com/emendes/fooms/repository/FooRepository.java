package com.emendes.fooms.repository;

import com.emendes.fooms.model.entity.Foo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FooRepository extends JpaRepository<Foo, Long> {
}
