package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDataDriven {
	
	User userPayload;
	Faker faker;
	
	@Test(priority = 1,dataProvider = "data",dataProviderClass = DataProviders.class)
	public void testPostRequest(String id,String username,String firstname,String lname,String email,String password,String phone) {
		userPayload=new User();
		
		userPayload.setId(Integer.parseInt(id));
		userPayload.setUsername(username);
		userPayload.setFirstname(firstname);
		userPayload.setLastname(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response = UserEndPoint.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2,dataProvider = "usernameData",dataProviderClass = DataProviders.class)
	public void testDeleteRequest() {
		Response response = UserEndPoint.deleteUser(userPayload.getUsername());
		response.then().statusCode(200);
	}
}
