package test.pickme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashSet;

public class ServicesTest extends BaseClass{

    String userId;
    @BeforeTest
        public void setting(){
        RestAssured.baseURI = "https://reqres.in/";
     }
    @Test(priority = 0)
    public void getUsers(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get("api/users?page=1");
        ResponseBody body = response.getBody();

        String res = body.asString();

        int statusCode=response.getStatusCode();
        System.out.println("Whole Response Body : "+res);

        HashSet idSet = new HashSet();
        idSet.addAll(response.jsonPath().getList("data.id"));
        Boolean is2=idSet.contains(2);
        Boolean is3=idSet.contains(3);

        System.out.println("Extracted user IDs :"+body.path("data.id"));
        System.out.println("Set of user IDs : "+idSet);

        Assert.assertEquals(statusCode,200);
        Assert.assertEquals(is2,true);
        Assert.assertEquals(is3,true);
    }
    @Test(priority = 1)
    public void createUser(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.post("api/users/");
        request.queryParam("name","abc");
        request.queryParam("job","leader");
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,201);

        ResponseBody body = response.getBody();
        String userId=body.path("id");
        Response res = request.get("api/users/"+userId);
        ResponseBody userData = res.getBody();

        String uId=userData.path("data.id");
        String cTime=userData.path("data.createdAt");
        System.out.println("Created user's id is "+userId);
        System.out.println("The user id is "+uId+" and the created time is "+cTime);
    }
}
