package com.emendes.barms.repository;

import com.emendes.barms.model.entity.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {
}
