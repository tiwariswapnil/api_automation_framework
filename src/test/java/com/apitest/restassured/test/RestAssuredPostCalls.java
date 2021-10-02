package com.apitest.restassured.test;

import com.apitest.integration.ApiIntegration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class RestAssuredPostCalls extends ApiIntegration {

    String endpoint = "http://localhost:8080/products";

    @Test
    public void invokePostApiCall() throws JSONException {
        baseURI = endpoint;
        RequestSpecification requestSpecification = given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", "3");
        requestParams.put("name", "Cashews");

        requestSpecification.header("Content-Type", "application/json");
        requestSpecification.body(requestParams.toString());

        Response response = requestSpecification.post(endpoint);
        assertEquals(response.getStatusCode(), 201);
        verifyGetCallResponseById("3", "Cashews");
    }

    public void verifyGetCallResponseById(String id, String name){
        get(endpoint + "/" + id).then().assertThat()
                .body("id", Matchers.equalTo(id))
                .body("name", Matchers.equalTo(name));
    }

}
