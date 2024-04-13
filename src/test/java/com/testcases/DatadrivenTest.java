package com.testcases;

import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.endpoints.UserEndPoints;
import com.github.javafaker.Faker;
import com.payload.User;
import com.utilities.ReadDataFromXcel;

import io.restassured.response.Response;

public class DatadrivenTest {
	//User userpayload;
	//Faker fa;
	
	
	@Test(priority=1,dataProvider="testdata",dataProviderClass=ReadDataFromXcel.class)
	public void readXlData(String uname,String fname, String lname, String email,String pword, String phno )
	{
	    Faker fa=new Faker();
		User userpayload= new User();
		userpayload.setUsername(uname);
		userpayload.setFirstname(fname);
		userpayload.setLastname(lname);
		userpayload.setEmailid(email);
		userpayload.setPassword(pword);
		userpayload.setPhoneno(phno);
		userpayload.setId(fa.idNumber().hashCode());
       Response res=UserEndPoints.createUser(userpayload);
		
		//res.then().log().all();
		Assert.assertEquals(res.getStatusCode(),200);
		
	}
	@Test(priority=2,dataProvider="userdata",dataProviderClass=ReadDataFromXcel.class)
	
	public void readSingleUser(String data)
	{
		Response res1=UserEndPoints.deleteUser(data);
		//res.then().log().all();
		Assert.assertEquals(res1.getStatusCode(),200);
		
		
	}

}
