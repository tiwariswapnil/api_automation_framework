package com.apitest.restassured.test;

import com.apitest.integration.ApiIntegration;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.get;

public class RestAssuredGetCalls extends ApiIntegration {

    String endpoint = "http://localhost:8080/products";

    @Test
    public void verifyIdInGetCallResponse() {
        get(endpoint + "/2").then().assertThat()
                .body("id", Matchers.equalTo("2"));
    }

    @Test
    public void verifyGetCallResponse() {
        get(endpoint + "/1").then().assertThat()
                .body("id", Matchers.equalTo("1"))
                .body("name", Matchers.equalTo("Honey"));
    }

    @Test
    public void invokeGetCall(){
        Response response = get(endpoint);
        response.getBody().print();
    }
}
