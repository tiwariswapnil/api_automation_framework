# API Automation Framework

## HTTP URL Connection
HttpUrlConnection is a built-in Java class which provides a way of performing HTTP requests in Java. The HttpUrlConnection class allows us to perform basic
HTTP requests without the use of any additional libraries. All the classes that we need are part of the java.net package. 

### Sample Usage

1. Creating a request
The HttpUrlConnection class is used for all types of requests by setting the requestMethod attribute to one of the values: GET, POST, HEAD, OPTIONS, PUT, DELETE, TRACE.
```
  URL url = new URL("http://example.com");
  HttpURLConnection con = (HttpURLConnection) url.openConnection();
  con.setRequestMethod("GET");
```

2. Setting Request Headers
Adding headers to a request can be achieved by using the setRequestProperty() method:
```
  con.setRequestProperty("Content-Type", "application/json");
```

3. Reading the Response
Reading the response of the request can be done by parsing the InputStream of the HttpUrlConnection instance.
```
  BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
  String inputLine; 
  StringBuffer content = new StringBuffer();
  while ((inputLine = in.readLine()) != null) {
    content.append(inputLine);
  }
  in.close();
```
Reference:
Refer to the `src/test/java/com/apitest/httpurlconnection/` package for creating HTTP requests using HttpURLConnection

## Rest Assured
REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs. REST Assured is a high level Java DSL for simplified testing of REST based services built over HTTP. HttpClient is used by REST Assured under the hood for Http communication.

### Sample Usage

1. Invoking a GET request
``` 
  get(endpoint).then().assertThat().statusLine(containsString("200"));
```

2. Using Response object for GET request
```
  Response response = get(endpoint);
  String statusLine = response.getStatusLine();
  System.out.println("Response statusLine: " + statusLine);
```

3. Invoke a POST request
```
  // Create a JSONObject and add the request parameters 
  JSONObject requestParams = new JSONObject();
  requestParams.put("id", "3");
  requestParams.put("name", "Cashews");
  
  // Create a RequestSpecification object and add header and body of the request
  RequestSpecification requestSpecification = given();
  requestSpecification.header("Content-Type", "application/json");
  requestSpecification.body(requestParams.toString());
  
  // Invoke POST call
  Response response = requestSpecification.post(endpoint);  
```
Reference:
Refer to the `src/test/java/com/apitest/restassured/test/` package for RestAssured tests
