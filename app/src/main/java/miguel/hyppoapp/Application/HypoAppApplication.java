package miguel.hyppoapp.Application;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;

import miguel.hyppoapp.ParseHandler;

/**
 * Created by Miguel on 12/21/2015.
 */
public class HypoAppApplication extends Application {
    public ParseHandler parseHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        parseHandler = ParseHandler.getInstance();
        try {
            parseHandler.loadData();
        } catch (ParseException e) {
            Log.e("Error loadData()", "error while loading data.");
            e.printStackTrace();
        }

        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

    }
}
