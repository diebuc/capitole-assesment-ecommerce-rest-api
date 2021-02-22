package com.diebuc.ecommerce.demo.service;

import com.diebuc.ecommerce.demo.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
    List<Price> getAllPrices();
    Price getCurrentPrice(Integer brandId, Long productId, LocalDateTime appDateTime);
}
