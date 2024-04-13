package com.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import com.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	public static Response createUser(User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		return response;
		
	}
	public static Response getUser(String userName)
	{
		Response response=given()
		
		.pathParam("username",userName)
		.when()
		.get(Routes.get_url);
		
		return response;
		
	}
	public static Response updateUser(String username, User payload)
	{
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.pathParam("username",username)
		.when()
		.put(Routes.put_url);
		
		return response;
	}
	public static Response deleteUser(String userName)
	{
		Response response=given()
		
		.pathParam("username",userName)
		.when()
		.delete(Routes.get_url);
		
		return response;
	}
	
	

}
	
