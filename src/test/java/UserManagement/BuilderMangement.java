package UserManagement;

import Utils.StatusCode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import utils.SoftAssertUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BuilderMangement {

    private RequestSpecification resquestSpec;
    private ResponseSpecification responseSpec;

    public RequestSpecification getRequestSpecificationBuilder(String pages)
    {
           resquestSpec=new RequestSpecBuilder().setBaseUri("https://reqres.in").
                   addQueryParam("page", pages).
                   addHeader("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").build();
           return resquestSpec;
    }

    public ResponseSpecification getResponseSpecification(int statusCOde,String contentType)
    {
        responseSpec=new ResponseSpecBuilder().expectContentType(contentType).expectStatusCode(statusCOde).build();
        return responseSpec;
    }

    @Test(groups = "smoke")
    public void getUser() {
        resquestSpec=getRequestSpecificationBuilder("2");
        responseSpec=getResponseSpecification(200,"application/json");
             given().spec(resquestSpec).
                when().get("/api/users").then().spec(responseSpec);

    }

}
