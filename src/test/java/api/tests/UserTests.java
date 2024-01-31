package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoint;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test
	public void testPostRequest() {
		Response response = UserEndPoint.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(dependsOnMethods = {"testPostRequest"})
	public void testGetRequest() {
		Response response = UserEndPoint.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
	}
	@Test(dependsOnMethods = {"testPostRequest","testGetRequest"})
	public void testPutRequest() {
		// update data
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setLastname(faker.name().lastName());
		
		Response response = UserEndPoint.updateUser(userPayload, userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(dependsOnMethods = {"testPostRequest","testGetRequest","testPutRequest"})
	public void testDeleteRequest() {
		Response response = UserEndPoint.deleteUser(userPayload.getUsername());
		response.then().statusCode(200);
	}
}