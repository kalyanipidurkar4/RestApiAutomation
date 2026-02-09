package UserManagement;
import Utils.StatusCode;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.SoftAssertUtil;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class TestListener {
    @Test()
    public void getUser() {
        Response resp = given().baseUri("https://rs.in").queryParam("page", "2").
                when().get("/api/users").then().assertThat().statusCode(StatusCode.SUCCESS.code).extract().response();

        System.out.println(resp.asPrettyString());
        // assertThat(resp.jsonPath().getInt("per_page"), is(6));
        SoftAssertUtil util=SoftAssertUtil.getInstance();
        util.assertEquals(resp.jsonPath().getInt("per_page"), 6, "per page is not 6");
        util.assertTrue(resp.getStatusCode() == 300, "status code is not 200");
        util.assertAll();
        assertThat(resp.jsonPath().getList("data.email"), hasItem("michael.lawson@reqres.in"));

        assertThat(resp.jsonPath().getList("data.last_name"), hasItem("Ferguson"));


    }
}

