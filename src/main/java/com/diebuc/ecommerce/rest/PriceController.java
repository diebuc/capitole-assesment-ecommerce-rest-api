package com.diebuc.ecommerce.rest;

import com.diebuc.ecommerce.dto.PriceDto;
import com.diebuc.ecommerce.entity.Price;
import com.diebuc.ecommerce.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class PriceController {

    @Autowired
    PriceService priceService;

    @RequestMapping("/PriceList")
    public List<Price> getAllPrices() {
        return priceService.getAllPrices();
    }

    @RequestMapping("/Price")
    public PriceDto getPrices(@RequestParam Integer brandId,
                              @RequestParam Long productId,
                              @RequestParam String applicationDateTime)  {

        LocalDateTime appDateTime = LocalDateTime.parse(applicationDateTime);

        Price price = priceService.getCurrentPrice(brandId,productId,appDateTime);

        return new PriceDto(
                price.getPriceId(),
                price.getProductId(),
                price.getBrandId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurrency()
        );

    }

}
