import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
  
public class Config 
{

    private JSONObject cfgJson;

    public Config() {

        try {

            cfgJson = (JSONObject) (new JSONParser().parse(new FileReader("config.json")));

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public String getUrl(String key) {

        return getData("adresses", key);
    }

    public String getEmail() {

        return getData("userdata", "email");
    }

    public String getPassword() {

        return getData("userdata", "password");
    }

    private String getData(String mainKey, String subKey) {

        Map map = (Map) cfgJson.get(mainKey);
        return (String) map.get(subKey);
    }

}