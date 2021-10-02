package com.apitest.httpurlconnection.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.api.models.Product;
import org.junit.Assert;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHelper {

    String endpoint = "http://localhost:8080/products";

    public HttpURLConnection httpCon;
    public String response = null;
    ObjectMapper objectMapper = new ObjectMapper();

    public String submitGetRequest(String getProductEndpoint) {

        try {

            URL url = new URL(endpoint + getProductEndpoint);

            httpCon = (HttpURLConnection) url.openConnection();

            httpCon.setRequestMethod("GET");
            httpCon.setRequestProperty("Accept", "application/json");

            httpCon.connect();

            Assert.assertEquals("Http Status Code is not as expected", 200, httpCon.getResponseCode());

            response = readResponseStream(httpCon.getInputStream());

            httpCon.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }

    public String submitPostRequest(Product product) {

        try {

            URL url = new URL(endpoint);

            httpCon = (HttpURLConnection) url.openConnection();

            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setDoOutput(true);

            httpCon.connect();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpCon.getOutputStream());

            outputStreamWriter.write(objectMapper.writeValueAsString(product));
            outputStreamWriter.flush();

            Assert.assertEquals("Http Status Code is not as expected", 201, httpCon.getResponseCode());

            response = readResponseStream(httpCon.getInputStream());

            httpCon.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }

    public String submitDeleteRequest(String deleteIdEndpoint) {

        try {

            URL url = new URL(endpoint + deleteIdEndpoint);

            httpCon = (HttpURLConnection) url.openConnection();

            httpCon.setRequestMethod("DELETE");
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setDoOutput(true);

            httpCon.connect();

            Assert.assertEquals("Http Status Code is not as expected", 200, httpCon.getResponseCode());

            response = readResponseStream(httpCon.getInputStream());

            httpCon.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }

    static String readResponseStream(InputStream stream) throws IOException {

        String inputLine;
        StringBuffer response = new StringBuffer();

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }
}
