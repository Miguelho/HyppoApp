package miguel.hyppoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private HyppoAdapter hyppoAdapter;
    private List<ParseObject> listaEstados = null;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ParseQuery<ParseObject> userQuery = ParseQuery.getQuery("State").whereEqualTo("Relation_User", ParseUser.getCurrentUser());

        try {
            listaEstados=userQuery.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*userQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    listaEstados = new ArrayList<ParseObject>();
                    for (ParseObject state: objects)
                    {
                        String courseName = state.getString("Name");
                        listaEstados.add(state);
                    }
                }else
                    e.printStackTrace();
            }
        });
*/

        lv = (ListView) findViewById(R.id.listView);
        hyppoAdapter = new HyppoAdapter(getApplicationContext(), 0, listaEstados);
        lv.setAdapter(hyppoAdapter);
    }

}
