package UserManagement;


import Listeners.ExtentTestListener;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Listeners;
import static Utils.JsonReader.getValueFromJsonArray;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Listeners(ExtentTestListener.class)
public class GetUsers {

    @Test(groups ={"smoke"})
    public void getUserWithHeaderNEW()
    {
       Response response =given().header("x-api-key","reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                baseUri("https://reqres.in/api")
                .when().get("/users?page=2").then().extract().response();

       //printing response
        response.then().log().all();

    }

    @Test(groups = "regression")
    public void getUserWithMultipleHeaderUsingMapNEW()
    {
        Map<String,String> headers=new HashMap<>();
        headers.put("x-api-key","reqres_952f5e0ffa4440fd823d2bd5d47de62f");
        headers.put("Content-Type","application/json");

        Response response =given().headers(headers).
                baseUri("https://reqres.in/api").queryParam("?page=2")
                .when().get("/users")
                .then().extract().response();

        //printing response
        response.then().log().all();


    }

    @Test(groups = {"regression","smoke"})
    public void verifyHeadersFromResponseNEW() {
        Response response = given().when().get("https://reqres.in/api/users?page=2").then().extract().response();

        Headers headers = response.headers();
        for (Header h : headers) {
            // System.out.println(h.getName()+":"+h.getValue());
            if (h.getName().contains("Content-Type")) {
                assertEquals(h.getValue(), "text/html; charset=UTF-8");
                System.out.println("Assertion validated successfully");
                System.out.println("Master code");
                System.out.println("assertion validated");
            }
        }
    }







}
