package miguel.hyppoapp.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miguel on 12/22/2015.
 */
public class ParseHandler {
    public static String HAPPYMOOD = "qAEiyzKOZu";
    public static String NORMALMOOD = "2kDjF5Hdaa";
    public static String SADMOOD = "yfSXhR3Ci3";

    public static Map<String,String> moodMap = new HashMap<>();



    public static Map<String,String> conditionMap = new HashMap<>();

    public ParseHandler(){
        moodMap.put("Happy","qAEiyzKOZu");
        moodMap.put("Normal","2kDjF5Hdaa");
        moodMap.put("Sad","yfSXhR3Ci3");

        conditionMap.put("Pain","x11TfeRz6h");
        conditionMap.put("Sleep","GOGvwLcZUS");
        conditionMap.put("Mental","757r5hhuPf");

    }
}
