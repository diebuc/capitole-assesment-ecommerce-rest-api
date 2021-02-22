package com.diebuc.ecommerce.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Price {
    @Id
    @GeneratedValue
    final Long priceId;
    final Integer brandId;
    final LocalDateTime startDate;
    final LocalDateTime endDate;
    final Integer priceList;
    final Long productId;
    final Short priority;
    final Double price;
    @Column(name = "CURR")
    final String currency;
}
