package com.diebuc.ecommerce.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test01() throws Exception {
        Long productId = 35455L;
        Integer brandId = 1;
        LocalDateTime priceDate = LocalDateTime.parse("2020-06-14 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
        final String urlTemplate = getUrlTemplate(productId, brandId, priceDate);

        final String expectedEndDate = "2020-12-31T23:59:59";
        final String expectedStartDate = "2020-06-14T00:00:00";
        final int expectedProductId = 35455;
        final double expectedPrice = 35.50;
        final int expectedBrandId = 1;
        assertPriceResponse(urlTemplate, expectedEndDate, expectedStartDate, expectedProductId, expectedPrice, expectedBrandId);
    }

    @Test
    public void test02() throws Exception {
        Long productId = 35455L;
        Integer brandId = 1;
        LocalDateTime priceDate = LocalDateTime.parse("2020-06-14 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
        final String urlTemplate = getUrlTemplate(productId, brandId, priceDate);
        final String expectedEndDate = "2020-06-14T18:30:00";
        final String expectedStartDate = "2020-06-14T15:00:00";
        final int expectedProductId = 35455;
        final double expectedPrice = 25.45;
        assertPriceResponse(urlTemplate, expectedEndDate, expectedStartDate, expectedProductId, expectedPrice, 1);
    }

    @Test
    public void test03() throws Exception {
        Long productId = 35455L;
        Integer brandId = 1;
        LocalDateTime priceDate = LocalDateTime.parse("2020-06-14 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
        final String urlTemplate = getUrlTemplate(productId, brandId, priceDate);
        final String expectedEndDate = "2020-12-31T23:59:59";
        final String expectedStartDate = "2020-06-14T00:00:00";
        final int expectedProductId = 35455;
        final double expectedPrice = 35.50;
        assertPriceResponse(urlTemplate, expectedEndDate, expectedStartDate, expectedProductId, expectedPrice, 1);
    }

    @Test
    public void test04() throws Exception {
        Long productId = 35455L;
        Integer brandId = 1;
        LocalDateTime priceDate = LocalDateTime.parse("2020-06-15 10:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
        final String urlTemplate = getUrlTemplate(productId, brandId, priceDate);
        final String expectedStartDate = "2020-06-15T00:00:00";
        final String expectedEndDate = "2020-06-15T11:00:00";
        final int expectedProductId = 35455;
        final double expectedPrice = 30.50;
        assertPriceResponse(urlTemplate, expectedEndDate, expectedStartDate, expectedProductId, expectedPrice, 1);
    }

    @Test
    public void test05() throws Exception {
        Long productId = 35455L;
        Integer brandId = 1;
        LocalDateTime priceDate = LocalDateTime.parse("2020-06-16 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00"));
        String urlTemplate = getUrlTemplate(productId, brandId, priceDate);
        final String expectedStartDate = "2020-06-15T16:00:00";
        final String expectedEndDate = "2020-12-31T23:59:59";
        final double expectedPrice = 38.95;
        final int expectedProductId = 35455;
        assertPriceResponse(urlTemplate, expectedEndDate, expectedStartDate, expectedProductId, expectedPrice, 1);
    }

    private String getUrlTemplate(Long productId, Integer brandId, LocalDateTime priceDate) {
        StringBuilder queryStringBuilder = new StringBuilder();
        queryStringBuilder.append("http://localhost:8080/Price?")
                .append("brandId=")
                .append(brandId)
                .append("&productId=")
                .append(productId)
                .append("&applicationDateTime=")
                .append(priceDate.toString());
        return queryStringBuilder.toString();
    }

    private void assertPriceResponse(String urlTemplate, String expectedEndDate, String expectedStartDate, int expectedProductId, double expectedPrice, int expectedBrandId) throws Exception {
        mvc.perform(post(urlTemplate).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currency", is("EUR")))
                .andExpect(jsonPath("$.price", is(expectedPrice)))
                .andExpect(jsonPath("$.endDate", is(expectedEndDate)))
                .andExpect(jsonPath("$.startDate", is(expectedStartDate)))
                .andExpect(jsonPath("$.productId", is(expectedProductId)))
                .andExpect(jsonPath("$.brandId", is(expectedBrandId)));
    }

}