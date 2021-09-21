package gitHub;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GitHubExample {
  @Test(enabled=false,description="get all repository for Authentic user")
  public void getAllRepository() {
	  given()
	  		.auth()				//Giving Authentication
	  		.oauth2("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")
	  .when()
	  		.get("https://api.github.com/user/repos")
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(200);
  }
  
  
  @Test(description="get all repository for Authentic user")
  public void createRepository() {
	  JSONObject para=new JSONObject();
	  para.put("name", "RestAssuredToolCreatedMe2");
	  para.put("description", "Sample for Post Request");
	  para.put("homepage", "http://github.com/vprem99");  
	  given()
	  		.auth()				//Giving Authentication
	  		.oauth2("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")
	  		.header("Content-Type","application/json")
	  		.body(para.toJSONString())
	  .when()
	  		.post("https://api.github.com/user/repos")
	  .then()
	  		.log()
	  		.body()
	  		.statusCode(201);
  }
}