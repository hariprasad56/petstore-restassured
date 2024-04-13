package com.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endpoints.UserEndPoints;
import com.github.javafaker.Faker;
import com.payload.User;

import io.restassured.response.Response;

public class VerifyCreateUser {
	Faker fa;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setUp()
	{
		fa= new Faker(); 
		userpayload= new User();
		userpayload.setId(fa.idNumber().hashCode());
		userpayload.setUsername(fa.name().username());
		userpayload.setFirstname(fa.name().firstName());
		userpayload.setLastname(fa.name().lastName());
		userpayload.setEmailid(fa.internet().emailAddress());
		userpayload.setPassword(fa.internet().password());
		userpayload.setPhoneno(fa.phoneNumber().cellPhone());
		logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
	public void verifyCreateUser()
	{
		Response res=UserEndPoints.createUser(userpayload);
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("*************user is created****************");
		
		
	}
	@Test(priority=2)
	public void VerifyGetUser()
	{
		Response res=UserEndPoints.getUser(this.userpayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		logger.info("*************get the user details****************");
		
	}
    @Test(priority=3)
	public void verifyUpdateUser()
	{
    	
    	userpayload.setFirstname(fa.name().firstName());
		userpayload.setLastname(fa.name().lastName());
		userpayload.setEmailid(fa.internet().emailAddress());
    	
		Response res=UserEndPoints.updateUser(this.userpayload.getUsername(),userpayload);
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		
		Response resAfter=UserEndPoints.getUser(this.userpayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(resAfter.getStatusCode(),200);
		
		logger.info("*************update the user details****************");
		
	}
    @Test(priority=4)
    public void VerifydeleteUser()
	{
		Response res=UserEndPoints.deleteUser(this.userpayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("*************delete the user****************");
		
	}
    
	

}
