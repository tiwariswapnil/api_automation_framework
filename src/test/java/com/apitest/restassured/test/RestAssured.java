package com.apitest.restassured.test;

import com.apitest.integration.ApiIntegration;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RestAssured extends ApiIntegration {

    String endpoint = "http://localhost:8080/products";

    @Test
    public void getHeadersCookiesAndStatus(){
        Response response = get(endpoint);
        Headers allHeaders = response.getHeaders();
        System.out.println("Response headers: " + allHeaders);

        Map<String, String> allCookies = response.getCookies();
        System.out.println("Response cookies: " + allCookies);

        String statusLine = response.getStatusLine();
        System.out.println("Response statusLine: " + statusLine);

        int statusCode = response.getStatusCode();
        System.out.println("Response statusCode: " + statusCode);
    }

    @Test
    public void verifyResponseHeadersAndStatus(){
        when().request("GET", endpoint).then().statusCode(200);
        get(endpoint).then().assertThat().statusCode(200);
        get(endpoint).then().assertThat().statusLine(containsString("200"));

        get(endpoint).then().assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void measureResponseTime() {
        Response response = io.restassured.RestAssured.get(endpoint);
        long timeInMS = response.time();
        long timeInS = response.timeIn(TimeUnit.SECONDS);
        assertEquals(timeInS, timeInMS/1000);

        when().get(endpoint).then().time(lessThan(5L),TimeUnit.SECONDS);

    }

}
