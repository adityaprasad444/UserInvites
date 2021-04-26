package com.trial;

import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import helpers.ConfigHelper;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import utilities.DBHelper;
import utilities.ExcelRead;

public class CreateTrialUsers {

	public ConfigHelper ch=new ConfigHelper();
	public DBHelper data= new DBHelper();


	@SuppressWarnings("unchecked")
	@Test(priority =1)
	public void CreateValidTrialUser(){
		RestAssured.baseURI=ch.testURL();
		HashMap<String,String> traildata=ExcelRead.readTestData("Trialusers","Validuser");
		RequestSpecification request=RestAssured.given();
		JSONObject authparam = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject requestParams = new JSONObject();
		requestParams.put("userId", Integer.parseInt(traildata.get("id")));
		requestParams.put("firstName",traildata.get("Fname")); 
		requestParams.put("lastName", traildata.get("Lname"));
		requestParams.put("email", traildata.get("email"));
			authparam.put("wsmCourse", Integer.parseInt(traildata.get("wsmCourse")));
			authparam.put("accessDays", Integer.parseInt(traildata.get("accessDays")));
			authparam.put("deliveryType", Integer.parseInt(traildata.get("deliveryType")));
			authparam.put("websiteUrl",traildata.get("websiteUrl"));
		authArray.add(authparam);
		requestParams.put("course",authparam);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString().toString());
		System.out.println(requestParams.toJSONString());
		Response res=request.request(Method.POST, "/trialAccess");
		int statusCode=res.getStatusCode();
		Assert.assertEquals(statusCode, 204, "Trial Access Not Success");
		data.startConnection("test");
		String DbData=data.getdata("id", "email", "userType", "users", "email", traildata.get("email").toLowerCase());
		Assert.assertEquals(DbData.contains("trial"), true, "Trial User not crated");
		//String user=DbData.substring(14,38);
		//data.deleteByObjectID("usercourses", "user", user);
		//data.deleteWithString("users", "email", traildata.get("email").toLowerCase());
		data.closeConnection();
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority =2)
	public void CreateExistingTrialUser(){
		RestAssured.baseURI=ch.testURL();
		HashMap<String,String> traildata=ExcelRead.readTestData("Trialusers","Validuser");
		RequestSpecification request=RestAssured.given();
		JSONObject authparam = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject requestParams = new JSONObject();
		requestParams.put("userId", Integer.parseInt(traildata.get("id")));
		requestParams.put("firstName",traildata.get("Fname")); 
		requestParams.put("lastName", traildata.get("Lname"));
		requestParams.put("email", traildata.get("email"));
			authparam.put("wsmCourse", Integer.parseInt(traildata.get("wsmCourse")));
			authparam.put("accessDays", Integer.parseInt(traildata.get("accessDays")));
			authparam.put("deliveryType", Integer.parseInt(traildata.get("deliveryType")));
			authparam.put("websiteUrl",traildata.get("websiteUrl"));
		authArray.add(authparam);
		requestParams.put("course",authparam);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString().toString());
		System.out.println(requestParams.toJSONString());
		Response res=request.request(Method.POST, "/trialAccess");
		int statusCode=res.getStatusCode();
		Assert.assertEquals(statusCode, 204, "Trial Access Not Success");
		data.startConnection("test");
		String DbData=data.getdata("id", "email", "status", "userinvitelogs", "email", traildata.get("email").toLowerCase());
		Assert.assertEquals(DbData.contains("failed"), true, "Trial User already exists for same course");
		String user=DbData.substring(14,38);
		data.deleteByObjectID("usercourses", "user", user);
		data.deleteWithString("users", "email", traildata.get("email").toLowerCase());
		data.closeConnection();
	}
}
