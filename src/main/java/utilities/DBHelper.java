package utilities;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

public class DBHelper {

	public PropertiesRead pr;
	public DBHelper() {

		pr=new PropertiesRead("/src/main/resources/Properties/DB.Properties");
	}

	public String host() {
		return pr.getProperty("host");
	}
	public String testuser() {
		return pr.getProperty("testuser");
	}
	public String stageuser() {
		return pr.getProperty("stageuser");
	}
	public String testDB() {
		return pr.getProperty("testDB");
	}
	public String stageDB() {
		return pr.getProperty("stageDB");
	}
	public String testpassword() {
		return pr.getProperty("testpassword");
	}
	public String stagepassword() {
		return pr.getProperty("stagepassword");
	}

	String doc="";
	MongoClient mongoClient;
	MongoDatabase db;

	public void startConnection() {
		MongoClientURI uri = new 
			 MongoClientURI("mongodb+srv://prismdevadmin:Y8aembJFy8yqaOsT@prismdev.qkhpp.mongodb.net/test?w=majority");
			 mongoClient = new MongoClient(uri);
			 db = mongoClient.getDatabase("iratest");
			System.out.println("Database connection created : "+ testDB());		
	}

	public void closeConnection() {
		mongoClient.close();
		System.out.println("Data Base Connection Closed ");
	}

	public void deleteWithString(String table, String key, String value) {
		MongoCollection<Document> collection = db.getCollection(table);
		collection.deleteOne(Filters.eq(key, value));
		System.out.println("Delete Success: " +value);
	}

	public void deleteByObjectID(String table, String key, String value) {
		MongoCollection<Document> collection = db.getCollection(table);
		collection.deleteOne(Filters.eq(key, new ObjectId(value)));
		System.out.println("Delete Success from " +table +" for " +key +" with " +value);
	}

	public String getdata(String field1, String field2, String field3, String table,String key, String Value ) {
		MongoCollection<Document> collection = db.getCollection(table);
		Document query = new Document();
		//Search for key by value 
		//E.g., key=email, Value="xyz@gmail.com"
		query.append(key,Value);
		Document projection = new Document();
		projection.append(field1, "$"+field1);
		projection.append(field2, "$"+field2);
		projection.append(field3, "$"+field3);
		Block<Document> processBlock = new Block<Document>() {
			public void apply(final Document document) {
				System.out.println(document.toJson());
				doc=document.toString();				
			}
		};
		collection.find(query).projection(projection).sort(Sorts.ascending("createdAt")).forEach(processBlock);
		System.out.println("Data base Get Query Success");
		return doc;
	} 
}
