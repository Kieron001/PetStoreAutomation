package api.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import api.utilities.XLUtility;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	// Note - Here we are using data from DataProvider (excel sheet) and not by using Faker API.
	
	// here we are doing demo
	// creating users by 1st method "Data" and deleting the users by using 2nd method "UserName"
	
	@Test(priority=1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname,
			String lname, String useremail, String pwd, String phoneno)
	
	// will create multiple users
	
	{
		User userPayload = new User(); // POJO
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phoneno);
		
		Response response = UserEndPoints.createUser(userPayload);	
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName)
	// will delete the users created in the first method by querying using the userName
	{	
		
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
