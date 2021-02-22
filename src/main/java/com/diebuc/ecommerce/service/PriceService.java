package com.diebuc.ecommerce.service;

import com.diebuc.ecommerce.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {
    List<Price> getAllPrices();
    Price getCurrentPrice(Integer brandId, Long productId, LocalDateTime appDateTime);
}
