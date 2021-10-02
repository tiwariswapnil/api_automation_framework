package com.apitest.httpurlconnection.test;

import com.apitest.httpurlconnection.helper.ApiHelper;
import com.apitest.integration.ApiIntegration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.api.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class HttpUrlConnection extends ApiIntegration {

    ApiHelper apiHelper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        apiHelper = new ApiHelper();
    }

    @Test
    public void verifyGetCallResponse() throws JsonProcessingException {

        Product product;
        product = objectMapper.readValue(apiHelper.submitGetRequest("/1"), Product.class);
        Assert.assertEquals("Id does not match", "1", product.getId());
        Assert.assertEquals("Name does not match", "Honey", product.getName());

    }

    @Test
    public void invokeGetCall() throws JsonProcessingException {

        Product[] products;
        products = objectMapper.readValue(apiHelper.submitGetRequest(""), Product[].class);
        log.info("Get call response:");

        for (Product product : products) {
            log.info("Id: {}", product.getId());
            log.info("Name: {}", product.getName());
        }

    }

    @Test
    public void invokePostCall() {

        String response = apiHelper.submitPostRequest(createProductRequest());

        Assert.assertEquals("Error adding the product", "Product is created successfully", response);

    }

    public Product createProductRequest(){

        Product product = new Product();
        product.setId("3");
        product.setName("Cashews");

        return product;

    }

    @Test
    public void invokeDeleteCall() {

        String response = apiHelper.submitDeleteRequest("/2");

        Assert.assertEquals("Error deleting the product", "Product is deleted successfully", response);

    }

}
