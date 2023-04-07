package api.test;

import org.apache.logging.log4j.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload; 
	
	public Logger logger; 
	
	@BeforeClass
	public void setup() {
		
		faker= new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	
	
	@Test(priority=1)
	public void testPostUser(){
		logger.info("************* Creating User ****************");
		Response response =UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************* User Created ****************");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("************* Reading User Info ****************");
		Response res = UserEndPoints2.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************* User Info displayed ****************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		logger.info("************* Updating User ****************");
		
		// update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response =UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response res = UserEndPoints2.readUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("************* User is Updated ****************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("************* Deleting User ****************");
		Response res = UserEndPoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//checking data after update
		Response resAfterDelete = UserEndPoints2.readUser(this.userPayload.getUsername());
		resAfterDelete.then().log().all();
		Assert.assertEquals(resAfterDelete.getStatusCode(), 404);
		
		logger.info("************* User Deleted ****************");
	}
	
	
	
}
