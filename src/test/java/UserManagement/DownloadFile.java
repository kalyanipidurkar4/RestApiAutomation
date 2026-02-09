package UserManagement;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DownloadFile {

    @Test
    public void downloadAfile() throws IOException {
        Response resp=given().baseUri("https://example.com").when().get("/download/testfile.txt").then().extract().response();
         //we are converting resp into byte array
        byte[] array =resp.asByteArray();

        File file=new File("src/main/resources/DemoDownload.txt");
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(array);
        fos.close();
    }
}
