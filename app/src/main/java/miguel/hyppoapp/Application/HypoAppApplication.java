package miguel.hyppoapp.Application;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Miguel on 12/21/2015.
 */
public class HypoAppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);

        /*ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/

    }
}
