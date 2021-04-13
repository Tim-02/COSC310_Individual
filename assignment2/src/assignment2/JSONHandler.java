package assignment2;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONHandler {
    private JSONObject json;

    public JSONHandler(String str){
        try {
            json = (JSONObject) new JSONParser().parse(str);
        } catch (ParseException e) {
            System.out.println("Parsing exception occurred:" + e);
        }
    }

    public String get(String key){
        return (String)json.get(key);
    }
}
