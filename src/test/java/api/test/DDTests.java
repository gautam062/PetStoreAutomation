package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	User userPayload; 

	@Test(dataProvider="Data", dataProviderClass= DataProviders.class,priority=1)
	public void testPostUser(String userid, String username, String fname, String lname, String useremail, String pwd, String ph){
		
		userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response =UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority = 2, dataProvider="UserNames",dataProviderClass= DataProviders.class)
	public void testDeleteUserByName(String username) {
		
		Response response = UserEndPoints.deleteUser(username);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
