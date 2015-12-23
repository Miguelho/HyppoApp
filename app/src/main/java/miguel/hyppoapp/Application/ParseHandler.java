package miguel.hyppoapp.Application;

import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Miguel on 12/22/2015.
 */
public class ParseHandler {
    public static Map<String, String> moodMap = new HashMap<>();
    public static Map<String, String> conditionMap = new HashMap<>();


    private static ParseHandler parseHandler = new ParseHandler();


    private ParseHandler(){}
    public static ParseHandler getInstance() {
        return parseHandler;
    }
    public static void loadData() throws ParseException {
        Log.v("Singleton", "Creado");
        List<ParseObject> moodsDb = ParseQuery.getQuery("Mood").find();
        List<ParseObject> conditionsDb = ParseQuery.getQuery("PhysicalState").find();

        for (ParseObject iteratorMoods : moodsDb) {
            moodMap.put(String.valueOf(iteratorMoods.get("Name")), iteratorMoods.getObjectId());
        }
        for (ParseObject iteratorConditions : conditionsDb) {
            conditionMap.put(String.valueOf(iteratorConditions.get("Name")), iteratorConditions.getObjectId());
        }


        /*moodMap.put("Happy", "qAEiyzKOZu");
        moodMap.put("Normal","2kDjF5Hdaa");
        moodMap.put("Sad","yfSXhR3Ci3");*/
        /*conditionMap.put("Pain","x11TfeRz6h");
        conditionMap.put("Sleep","GOGvwLcZUS");
        conditionMap.put("Mental","757r5hhuPf");*/

    }
}

