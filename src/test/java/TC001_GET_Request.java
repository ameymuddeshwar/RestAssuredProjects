import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Request {
	
	@Test
	void getListOfUsers() {
		//Specify base UI
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Create a request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Create a response object
		Response response = httpRequest.request(Method.GET, "/users	");
		
		//Print response body in console
		String responseBody = response.getBody().asString();
		System.out.print(responseBody);
		
	}
}
