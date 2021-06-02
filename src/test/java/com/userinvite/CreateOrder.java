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
		JSONObject orderDetails = new JSONObject();

		orderDetails.put("orderCode",RandomHelper.getDateAndTime());
		orderDetails.put("orderStatus", "received");
		orderDetails.put("orderType","corporate");
		orderDetails.put("website","knowledgehut");
		orderDetails.put("userId",RandomHelper.getRandomNumber());
		orderDetails.put("firstName",RandomHelper.getTodayDate());
		orderDetails.put("lastName",RandomHelper.getTodayDate());
		orderDetails.put("email",RandomHelper.getDateAndTime()+"@mailinator.com");
		orderDetails.put("mobile","9999999999");
		orderDetails.put("countryId",4);
		orderDetails.put("cityId",15);
		orderDetails.put("state","karnataka");
		orderDetails.put("regionId",6);
		orderDetails.put("timezone","Asia/Calcutta");
			authparam.put("wsmCourse","208");
			authparam.put("deliveryType", 3);
		orderDetails.put("course",authparam);

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
			participants2.put("email",RandomHelper.getDateAndTime()+"@yopmail.com");
		ObjectNode participants3 = objectMapper.createObjectNode();
			participants3.put("userId",RandomHelper.getRandomNumber());
			participants3.put("firstName",RandomHelper.getDateAndTime());
			participants3.put("lastName",RandomHelper.getDateAndTime());
			participants3.put("email",RandomHelper.getRandomNumber()+RandomHelper.getDateAndTime()+"@mailinator.com");	
		parentArray.add(participants1);
		parentArray.add(participants2);
		parentArray.add(participants3);

		orderDetails.put("participants",parentArray);
		request.header("Content-Type", "application/json");
		request.body(orderDetails.toJSONString().toString());
		System.out.println(orderDetails.toJSONString());
		Response res=request.request(Method.POST, "/create");
	}
}
