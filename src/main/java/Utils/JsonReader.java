package Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JsonReader {

    public static String getdataFromJsonFile(String key) throws IOException {
        String data = String.valueOf(getJsonObject().get(key));
        return data;
    }

   /* public static JsonObject getJsonObject() throws IOException {
        File file=new File("C:\\Users\\kalya\\IdeaProjects\\RestAssuredPractiseFramework\\src\\main\\resources\\TestData.json");
        System.out.println("File existed"+file.exists());
        //reading file as a string as we can't directly read json file
        String s=FileUtils.readFileToString(file,"UTF-8");
        Object obj= JsonParser.parseString(s);
        JsonObject jsonobj= (JsonObject) obj;
        return jsonobj;
    }*/

    public static JsonObject getJsonObject() throws IOException {
        InputStream is = JsonReader.class
                .getClassLoader()
                .getResourceAsStream("TestData.json");

        if (is == null) {
            throw new RuntimeException("TestData.json NOT FOUND in resources folder");
        }

        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        return JsonParser.parseString(json).getAsJsonObject();
    }


    public static JsonArray GetJsonArrayValues(String key) throws IOException {
     JsonObject jsonObject =getJsonObject();
        System.out.println(JsonReader.getJsonObject());
        return jsonObject.getAsJsonArray(key);


    }

    public static Object getValueFromJsonArray(String key,int index) throws IOException {
        JsonArray languages=GetJsonArrayValues(key);
        return languages.get(index).getAsString();
    }

}
