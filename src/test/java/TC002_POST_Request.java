import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import utilities.RestUtils;

public class TC002_POST_Request {

	String empName = RestUtils.empName();
	String job = RestUtils.job();
	
	@Test
	void createUser() {
		//Specify base UI
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Create a request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request payload
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("job", job);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		Response response = httpRequest.request(Method.POST, "/users");
		
		//Print response body in console
		String responseBody = response.getBody().asString();
		System.out.print(responseBody);
		
		//Get status code
		int statusCode = response.getStatusCode();
		System.out.print(statusCode);
		
		String id = response.jsonPath().get("id");
		System.out.print("id: " +id);
		
		Headers allHeader = response.headers();
		for(Header header : allHeader) {
			System.out.println(header.getName()+"	-	"+header.getValue());
		}
	}
}
