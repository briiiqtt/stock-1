package com.briiiqtt.stockking.stock;

import com.briiiqtt.stockking.stock.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findById(Long id);
    Price save(Price price);
}
