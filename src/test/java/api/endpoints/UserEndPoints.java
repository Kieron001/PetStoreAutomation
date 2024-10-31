package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created to perform CRUD operations

public class UserEndPoints {
	
	
	// Create User
	public static Response createUser(User payload)
	{
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url); // from Routes.java
		
		return response;
		
	}
	
	// GET User
	public static Response readUser(String userName)
	{
		Response response = 
		given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url); // from Routes.java
		
		return response;
		
	}
	
	// Update User
	public static Response updateUser(String userName, User payload)
	{
		Response response = 
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.update_url); // from Routes.java
		
		return response;
		
	}
	
	// Delete User
	public static Response deleteUser(String userName)
	{
		Response response = 
		given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url); // from Routes.java
		
		return response;
		
	}
	
	
	
	
}
