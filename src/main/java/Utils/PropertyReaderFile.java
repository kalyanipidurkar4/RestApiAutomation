package Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReaderFile {

    public static String getDataFromPropertyFile(String filepath,String key)
    {
        String str=null;
        try {
            InputStream stream = new FileInputStream(filepath);
            Properties prop=new Properties();
            prop.load(stream);
           str= prop.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return str;
    }

}
