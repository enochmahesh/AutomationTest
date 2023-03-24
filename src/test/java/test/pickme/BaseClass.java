package test.pickme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

    public static void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
    }
}
