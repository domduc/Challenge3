package helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonHelper {
    static String _directory = System.getProperty("user.dir");

    public static JSONObject parsetoJson(String _strFileName) {
        String strFileFolder = String.format(_directory.concat("\\src\\test\\java\\testdata\\%s"), _strFileName);
        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;
        try {
            FileReader reader = new FileReader(strFileFolder);
            json = (JSONObject) jsonParser.parse(reader);
        } catch (Exception e) {

        }
        return json;
    }

}
