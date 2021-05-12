package com.userinvite;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigHelper;
import utilities.DBHelper;
import utilities.RandomHelper;

public class CreateOrder {

	public ConfigHelper ch=new ConfigHelper();
	public DBHelper data= new DBHelper();
	
	@SuppressWarnings("unchecked")
	@Test
	public void createOrder() {
		RestAssured.baseURI=ch.testURL();
		RequestSpecification request=RestAssured.given();
		JSONObject authparam = new JSONObject();
		JSONObject participants= new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("orderCode",RandomHelper.getDateAndTime());
		requestParams.put("orderStatus", "received");
		requestParams.put("orderType","corporate");
		requestParams.put("website","knowledgehut");
		requestParams.put("userId",RandomHelper.getRandomNumber());
		requestParams.put("firstName",RandomHelper.getTodayDate());
		requestParams.put("lastName",RandomHelper.getTodayDate());
		requestParams.put("email",RandomHelper.getDateAndTime()+"@mailinator.com");
		requestParams.put("mobile","9999999999");
		requestParams.put("countryId",4);
		requestParams.put("cityId",15);
		requestParams.put("state","karnataka");
		requestParams.put("regionId",6);
		requestParams.put("timezone","Asia/Calcutta");
			authparam.put("wsmCourse","208");
			authparam.put("deliveryType", 3);
		requestParams.put("course",authparam);
		
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayNode parentArray =  objectMapper.createArrayNode();
		ObjectNode participants1 = objectMapper.createObjectNode();
			participants1.put("userId",RandomHelper.getRandomNumber());
			participants1.put("firstName",RandomHelper.getDateAndTime());
			participants1.put("lastName",RandomHelper.getDateAndTime());
			participants1.put("email",RandomHelper.getDateAndTime()+"@mailinator.com");
		ObjectNode participants2 = objectMapper.createObjectNode();
			participants2.put("userId",RandomHelper.getRandomNumber());
			participants2.put("firstName",RandomHelper.getDateAndTime());
			participants2.put("lastName",RandomHelper.getDateAndTime());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			participants2.put("email",RandomHelper.getDateAndTime()+"@mailinator.com");
		parentArray.add(participants1);
		parentArray.add(participants2);	
		requestParams.put("participants",parentArray);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString().toString());
		System.out.println(requestParams.toJSONString());
		Response res=request.request(Method.POST, "/create");
	}
}
