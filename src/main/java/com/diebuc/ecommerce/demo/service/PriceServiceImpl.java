package com.diebuc.ecommerce.demo.service;

import com.diebuc.ecommerce.demo.entity.Price;
import com.diebuc.ecommerce.demo.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceRepository repository;

    @Override
    public List<Price> getAllPrices() {
        return repository.findAll();
    }

    @Override
    public Price getCurrentPrice(Integer brandId, Long productId, LocalDateTime appDateTime) {

        LocalDateTime applicationDateTimeFrom = appDateTime;
        LocalDateTime applicationDateTimeTo = applicationDateTimeFrom.plusSeconds(1);

        Price price =  repository.findDistinctTop1ByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanOrderByPriorityDesc(
                brandId,
                productId,
                applicationDateTimeFrom,
                applicationDateTimeTo
        );

        return price;

    }

}
