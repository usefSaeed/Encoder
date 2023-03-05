import Model.Item;
import Model.Menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class JSONReader {

    public Menu readMenu(String path) throws IOException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path))
        {
            Object obj = jsonParser.parse(reader);
            JSONObject menuObj = (JSONObject) obj;
            JSONObject menu = (JSONObject) menuObj.get("menu");
            String header = (String) menu.get("header");
            Menu m = new Menu(header);
            JSONArray arr = (JSONArray) menu.get("items");
            ArrayList<Item> items = parseItemArray(arr);
            m.setItems(items);
            return m;
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Item> parseItemArray(JSONArray arr) {
        ArrayList<Item> res = new ArrayList<>();
        for (Object o : arr) {
            JSONObject item = (JSONObject) o;
            if (item != null) {
                String id = (String) item.get("id");
                String label = (String) item.get("label");
                if (label == null) res.add(new Item(id));
                else res.add(new Item(id, label));
            } else {
                res.add(null);
            }
        }
        return res;
    }

}

