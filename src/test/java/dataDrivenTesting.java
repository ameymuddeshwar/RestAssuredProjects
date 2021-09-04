import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.XLUtils;

public class dataDrivenTesting {

	@Test(dataProvider="testData")
	void createUser(String uname, String job, String asd, String dfg) {
		
		//Specify base UI
		RestAssured.baseURI = "https://reqres.in/api";
		
		//Create a request object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Request payload
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", uname);
		requestParams.put("job", job);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		Response response = httpRequest.request(Method.POST, "/users");
		
		//Print response body in console
		String responseBody = response.getBody().asString();
		System.out.print(responseBody);
	}
	
	@DataProvider(name="testData")
	String[][] getTestData() throws IOException {
		
		String path = System.getProperty("user.dir")+"/src/test/java/TestDataFile.xlsx";
		
		int rowNos = XLUtils.getRowCount(path, "Sheet 1");
		int colNos = XLUtils.getCelCount(path, "Sheet 1", 1);
		
		String testXL[][] = new String[rowNos][colNos];
		
		for(int i=1; i<=rowNos; i++) {
			for(int j=0; j<colNos; j++) {
				testXL[i-1][j] = XLUtils.GetCelData(path, "Sheet 1", i, j);
			}
		}
		
		//String testData[][] = {{"Ajay", "Engineer"}, {"Anya", "Real Estate Agent"}, {"Pratik", "IT"}}; 	
		return testXL;
	}
}
