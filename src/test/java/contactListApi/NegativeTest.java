package contactListApi;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTest {
	@Test(enabled=true,description="Getting Contact which does not exist")
	public void getSpecificContact() {
		System.out.println("Getting Contact");
					given()
					.when()
						.get("http://3.13.86.142:3000/contacts/5")
					.then()
						.log()			//Print
						.body()
						.statusCode(404);   // Assertion
	}	

	@Test(enabled=false,description="Add Contact with Missing Email ID")
	public void addContactWithMissingEmailID() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Amy");
		data.put("lastName", "Sharma");
		//data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals(err,"Contacts validation failed: email: Email is required");
	}

	@Test(enabled=false,description="Negative Test with too many Character")
	public void addContactWithMoreSize() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "MohitMohitMohitMohitMohitMohitMohitMohitMohitMohit");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals( err.contains("is longer than the maximum allowed length"),true);
	}
/*
	4.Parameter has Invalid Characters : Pass Numbers in FirstName|LastName
	5.Parameter is not in Proper Format  > @ is missing in Email ID */
	@Test(enabled=true,description="Negative Test with invalid  Character")
	public void addContactWithNumberInName() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "123458");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99@gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals( err.contains("Contacts validation failed"),true);
	}
	@Test(enabled=true,description="Negative Test with invalid  Format")
	public void addContactWithInvalidFormar() {
		System.out.println("Creating Contact");
		JSONObject data=new JSONObject();
		JSONObject emp=new JSONObject();
		JSONObject loc=new JSONObject();
		emp.put("jobTitle", "QA");
		emp.put("company", "LTI");
		loc.put("city", "Mumbai");
		loc.put("country", "India");
		
		data.put("firstName", "Mayur");
		data.put("lastName", "Sharma");
		data.put("email", "mpremchand99gmail.com");
		data.put("location", loc);
		data.put("employer", emp);
		
			String err=	 given()
						.header("Content-Type","application/json")
						.body(data.toJSONString())
					.when()
						.post("http://3.13.86.142:3000/contacts")
					.then()
						.log()			//Print
						.body()
						.statusCode(400)   // Assertion
						.extract().path("err");
				Assert.assertEquals( err.contains("Contacts validation failed"),true);
	}
}
