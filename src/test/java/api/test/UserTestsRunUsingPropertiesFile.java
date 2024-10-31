package api.test;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsRunUsingPropertiesFile;
import api.payload.User;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTestsRunUsingPropertiesFile {
	
	
	Faker faker; // for dummy data
	User userPayload; // to send test data to the POJO class
	
	//public Logger logger;
	
	@BeforeClass
	public void setupData()  // To create Test Data before running the tests on the same.
	{
		
		
		faker = new Faker();
		userPayload = new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs with help of log4j2
		//logger = LogManager.getLogger(this.getClass()); Error
		// logger.debug("debugging...");
		
		
	}
	
	
	@Test(priority=1)
	public void testPostUser()
	{	
		//logger.info("************  Creating user ******************");
		
		Response response = UserEndPointsRunUsingPropertiesFile.createUser(userPayload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//logger.info("************  User is created ******************");
	}
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response = UserEndPointsRunUsingPropertiesFile.readUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200); // or response.StatusCode(200); without Assert
		
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{	
		
		// update data using payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		
		response.then().log().body().statusCode(200); // Chai assertion
		
		Assert.assertEquals(response.getStatusCode(), 200); // TestNG assertion
		
		// Check data after update
		Response responseAfterUpdate = UserEndPointsRunUsingPropertiesFile.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		Response response = UserEndPointsRunUsingPropertiesFile.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
