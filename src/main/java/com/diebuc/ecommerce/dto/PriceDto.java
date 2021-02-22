package com.diebuc.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class PriceDto implements Serializable {
    private final Long priceId;
    private final Long productId;
    private final Integer brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Double price;
    private final String currency;
}
