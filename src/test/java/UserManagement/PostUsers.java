package UserManagement;

import Pojo.CityPojo;
import Pojo.PostPayloadPojo;
import Utils.JsonReader;
import Utils.PropertyReaderFile;
import Utils.StatusCode;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.function.IOStream;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostUsers {

    private static FileInputStream FileinputStreamMethod(String requestBodyFilename)
    {
        FileInputStream input;
        try {
             input=new FileInputStream(new File(System.getProperty("user.dir")+"//src//main//resources//testData//"+requestBodyFilename));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return input;
    }
    @Test
    public void LoginFailureUsingAutomatingBodyAsString() {
        Response resp = given().baseUri("https://reqres.in/").body("{\n" +
                        "  \"email\": \"peter@klaven\"\n" +
                        "}").header("Content-type", "application/json").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                when().post("api/login").then().statusCode(400).extract().response();

        System.out.println(resp.asPrettyString());
        assertThat(resp.jsonPath().getString("error"), is("Missing password"));
    }

    @Test
    public void LoginFailureUsingAutomatingBodyAsExternalJsonFile() {
        Response resp = given().baseUri("https://reqres.in/").body(FileinputStreamMethod("postUser.json")).header("Content-type", "application/json").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                when().post("api/login").then().statusCode(400).extract().response();

        System.out.println(resp.asPrettyString());
        assertThat(resp.jsonPath().getString("error"), is("Missing password"));
    }

    @Test()
    public void createUser() throws IOException {
        PostPayloadPojo payload=new PostPayloadPojo();
       payload.setName("morpheus");
       payload.setJob("leader");
        String baseURI = PropertyReaderFile.getDataFromPropertyFile("C:\\Users\\kalya\\IdeaProjects\\RestAssuredPractiseFramework\\env.properties", "server");
        String endpoint = JsonReader.getdataFromJsonFile("endpoint");
        Response resp = given().baseUri(baseURI).contentType(ContentType.JSON).
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").body(payload).
                when().post(endpoint).then().statusCode(StatusCode.CREATED.code).
                extract().response();
        System.out.println(resp.asPrettyString());
        assertThat(StatusCode.CREATED.msg, is("Resource successfully created"));
        //assertThat(resp.jsonPath().getString("_meta.variant"), is("v1_b"));
    }

    @Test()
    public void createUserListPojo() throws IOException {
        PostPayloadPojo payload=new PostPayloadPojo();
        payload.setName("morpheus");
        payload.setJob("leader");
        List<String> language=new ArrayList<>();
        language.add("Hindi");
        language.add("Marathi");
        language.add("English");
        payload.setLanguage(language);
        String baseURI = PropertyReaderFile.getDataFromPropertyFile("C:\\Users\\kalya\\IdeaProjects\\RestAssuredPractiseFramework\\env.properties", "server");
        String endpoint = JsonReader.getdataFromJsonFile("endpoint");
        Response resp = given().baseUri(baseURI).contentType(ContentType.JSON).
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").body(payload).
                when().post(endpoint).then().statusCode(StatusCode.CREATED.code).
                extract().response();
        System.out.println(resp.asPrettyString());
        assertThat(StatusCode.CREATED.msg, is("Resource successfully created"));
        //assertThat(resp.jsonPath().getString("_meta.variant"), is("v1_b"));
    }

    @Test()
    public void createUserComplexPojoList() throws IOException {
        PostPayloadPojo payload=new PostPayloadPojo();
        payload.setName("morpheus");
        payload.setJob("leader");
        List<String> language=new ArrayList<>();
        language.add("Hindi");
        language.add("Marathi");
        language.add("English");
        payload.setLanguage(language);

        List<CityPojo> cityPojoList=new ArrayList<>();

        CityPojo citytemp1=new CityPojo();
        citytemp1.setCity("Delhi");
        citytemp1.setTemperature(45);

        CityPojo citytemp2=new CityPojo();
        citytemp1.setCity("Wani");
        citytemp1.setTemperature(30);

        cityPojoList.add(citytemp1);
        cityPojoList.add(citytemp2);

        payload.setCitypojoList(cityPojoList);

        String baseURI = PropertyReaderFile.getDataFromPropertyFile("C:\\Users\\kalya\\IdeaProjects\\RestAssuredPractiseFramework\\env.properties", "server");
        String endpoint = JsonReader.getdataFromJsonFile("endpoint");
        Response resp = given().baseUri(baseURI).contentType(ContentType.JSON).
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").body(payload).
                when().post(endpoint).then().statusCode(StatusCode.CREATED.code).
                extract().response();
        System.out.println(resp.asPrettyString());
        assertThat(StatusCode.CREATED.msg, is("Resource successfully created"));
        //assertThat(resp.jsonPath().getString("_meta.variant"), is("v1_b"));
    }




    @Test
    public void updateUserWithPojo() {
        PostPayloadPojo putPayload=new PostPayloadPojo();
        putPayload.setName("Sandeep");
        putPayload.setJob("Techleader");
        Response resp = given().baseUri("https://reqres.in/api/").
                body(putPayload).
                header("Content-type", "application/json").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                when().put("users/2").then()
                .statusCode(200).extract().response();

        System.out.println(resp.asPrettyString());

        assertThat(resp.jsonPath().getString("name"), is("Sandeep"));
        assertThat(resp.jsonPath().getString("job"), is("Techleader"));
    }

    @Test
    public void updateUserWithDeserializePojo() {
        PostPayloadPojo putPayload=new PostPayloadPojo();
        putPayload.setName("Sandeep");
        putPayload.setJob("Techleader");
        Response resp = given().baseUri("https://reqres.in/api/").
                body(putPayload).
                header("Content-type", "application/json").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                when().put("users/2").then()
                .statusCode(200).extract().response();

       Pojo.PostPayloadPojo pojoBodyResp = given().baseUri("https://reqres.in/api/").
                body(putPayload).
                header("Content-type", "application/json").
                header("x-api-key", "reqres_952f5e0ffa4440fd823d2bd5d47de62f").
                when().put("users/2").then()
                .statusCode(200).extract().as(PostPayloadPojo.class);

        assertThat(pojoBodyResp.getJob(),is("Techleader"));
        assertThat(pojoBodyResp.getName(),is("Sandeep"));
        System.out.println(resp.asPrettyString());

        assertThat(resp.jsonPath().getString("name"), is("Sandeep"));
        assertThat(resp.jsonPath().getString("job"), is("Techleader"));
    }
}
