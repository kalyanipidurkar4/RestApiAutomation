package UserManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadFile {

    @Test
    public void uploadingFile()
    {
        File file=new File("src/main/resources/Demo.txt");
        Response resp=given().baseUri("https://example.com").multiPart("file",file).
                when().post("/upload").then().extract().response();

        System.out.println(resp.asPrettyString());
    }
}
