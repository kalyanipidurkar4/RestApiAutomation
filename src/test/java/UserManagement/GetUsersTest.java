package UserManagement;

import Utils.JsonReader;
import Utils.PropertyReaderFile;
import com.google.gson.JsonArray;
import utils.SoftAssertUtil;
import Utils.StatusCode;
import com.google.gson.JsonElement;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static Utils.JsonReader.GetJsonArrayValues;
import static Utils.JsonReader.getValueFromJsonArray;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetUsersTest {

    @Test(groups = "smoke")
    public void getUser() {
        Response resp = given().baseUri("https://reqres.in").queryParam("page", "2").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
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

    @Test(groups = "smoke")
    public void createUser() throws IOException {
        String baseURI = PropertyReaderFile.getDataFromPropertyFile("C:\\Users\\kalya\\IdeaProjects\\RestAssuredPractiseFramework\\env.properties", "server");
        String endpoint = JsonReader.getdataFromJsonFile("endpoint");
        Response resp = given().baseUri(baseURI).contentType(ContentType.JSON).
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"leader\"\n" +
                        "}").
                when().post(endpoint).then().statusCode(StatusCode.CREATED.code).
                extract().response();

        System.out.println(resp.asPrettyString());
        assertThat(StatusCode.CREATED.msg, is("Resource successfully created"));
        //assertThat(resp.jsonPath().getString("_meta.variant"), is("v1_b"));
    }

    @Test(groups = "regression")
    public void updateUser() {
        Response resp = given().baseUri("https://reqres.in/api/").
                body("{\n" +
                        "  \"name\": \"Kartik\",\n" +
                        "  \"job\": \"the one\"\n" +
                        "}").
                header("Content-type", "application/json").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                when().put("users/2").then()
                .statusCode(200).extract().response();

        System.out.println(resp.asPrettyString());

        assertThat(resp.jsonPath().getString("name"), is("Kartik"));
        assertThat(resp.jsonPath().getString("job"), is("the one"));
    }


    @DataProvider(name = "testdata")
    public Object[][] testdata() {
        return new Object[][]
                {
                        {"kalyani", "leader"},
                        {"vedu", "team leader"},
                        {"kartik", "manager"}
                };
    }


    @Test(dataProvider = "testdata")
    public void createUserWithMultipleDataSets(String name, String job) throws IOException {
        String baseURI = PropertyReaderFile.getDataFromPropertyFile("C:\\Users\\kalya\\IdeaProjects\\RestAssuredPractiseFramework\\env.properties", "server");
        String endpoint = JsonReader.getdataFromJsonFile("endpoint");
        Response resp = given().baseUri(baseURI).contentType(ContentType.JSON).
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").body("{\n" +
                        "\"name\": \"" + name + "\",\n" +
                        "  \"job\": \"" + job + "\"\n" +
                        "}").
                when().post(endpoint).then().statusCode(StatusCode.CREATED.code).
                extract().response();

        System.out.println(resp.asPrettyString());
        assertThat(StatusCode.CREATED.msg, is("Resource successfully created"));
        //assertThat(resp.jsonPath().getString("_meta.variant"), is("v1_b"));
    }


    @Test
    public void readValuesFromJsonReaderArray() throws IOException {
        System.out.println(
                getValueFromJsonArray("Languages", 2));
        //reading complex json array
        JsonArray array =GetJsonArrayValues("Contacts");
        Iterator<JsonElement> itr =array.iterator();
        System.out.println("Elements of complex Json::");
        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }
    }


}