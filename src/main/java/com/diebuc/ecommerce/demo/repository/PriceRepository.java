package com.diebuc.ecommerce.demo.repository;


import com.diebuc.ecommerce.demo.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.server.ServerHttpRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdOrderByPriorityAsc(Integer brandId, Long productId);

    Price findDistinctTop1ByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanOrderByPriorityDesc(Integer brandId, Long productId, LocalDateTime apply1, LocalDateTime apply2);

}