package com.trial;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoIterable;

import utilities.DBHelper;

public class Test{

	public static void main(String[] args) {

		DBHelper db=new DBHelper();
		db.startConnection("test");
		String data=db.getdata("id", "email", "status", "userinvitelogs", "email", "trialuserwithsplchars@yopmail.com");
		db.closeConnection();
		
		System.out.println(data);
		//System.out.println(data.substring(14,38));
		//System.out.println(data.substring(80,95));
		//System.out.println(data.substring(50,100));
		
	}

}
