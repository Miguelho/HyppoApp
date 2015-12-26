package miguel.hyppoapp;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Miguel on 12/22/2015.
 */
public class ParseHandler {
    public Map<String, String> moodMap = new HashMap<>();
    public Map<String, String> conditionMap = new HashMap<>();


    private static ParseHandler parseHandler = null;


    private ParseHandler() {
    }

    public static ParseHandler getInstance() {
        if (parseHandler == null)
            parseHandler = new ParseHandler();
        return parseHandler;
    }

    public void loadData() throws ParseException {
        Log.v("Singleton", "Creado");
        List<ParseObject> moodsDb = ParseQuery.getQuery("Mood").find();
        List<ParseObject> conditionsDb = ParseQuery.getQuery("PhysicalState").find();

        for (ParseObject iteratorMoods : moodsDb) {
            moodMap.put(String.valueOf(iteratorMoods.get("Name")), iteratorMoods.getObjectId());
            //Happy object id
        }
        for (ParseObject iteratorConditions : conditionsDb) {
            String objectId = iteratorConditions.getObjectId();
            String name = String.valueOf(iteratorConditions.get("Name"));
            conditionMap.put(objectId, name);
        }

        /*moodMap.put("Happy", "qAEiyzKOZu");
        moodMap.put("Normal","2kDjF5Hdaa");
        moodMap.put("Sad","yfSXhR3Ci3");*/
        /*conditionMap.put("Pain","x11TfeRz6h");
        conditionMap.put("Sleep","GOGvwLcZUS");
        conditionMap.put("Mental","757r5hhuPf");*/

    }

    public String returnKey(String objectId) {
        if (objectId.equals("yfSXhR3Ci3"))
            return "Sad";
        else if (objectId.equals("2kDjF5Hdaa"))
            return "Normal";
        else
            return "Happy";
    }
}

