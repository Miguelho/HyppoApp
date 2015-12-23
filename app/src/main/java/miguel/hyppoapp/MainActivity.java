package miguel.hyppoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import miguel.hyppoapp.Application.ParseHandler;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private String errorMessage = "", mood = "", pain = "", sleep = "", mental = "";
    private TextView nameLabel, happyLabel, normalLabel, sadLabel;
    private ParseHandler parseHandler;
    private boolean flagConditions = false;
    private ParseObject condition = null, condition1 = null, condition2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIfCurrentUser();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nameLabel = (TextView) findViewById(R.id.tvUserName);
        //happyLabel = (TextView) findViewById(R.id.happyLabel);
        //normalLabel = (TextView) findViewById(R.id.normal_Label);
        //sadLabel = (TextView) findViewById(R.id.sadLabel);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkForm()) {
                    Toast.makeText(getApplicationContext(), "Registro enviado", Toast.LENGTH_LONG).show();
                    addStates();
                } else {
                    generarDialogo(errorMessage).show();
                }
            }
        });


    }


    private boolean checkForm() {
        if (!mood.equals("")) {
            return true;
        }

        return false;
    }

    private void addStates() {
        String moodString = parseHandler.moodMap.get(mood);
        ParseObject mood = new ParseObject("Mood");
        mood.setObjectId(moodString);

        ParseObject state = new ParseObject("State");
        state.put("Relation_Mood", mood);
        state.put("Relation_User", ParseUser.getCurrentUser());
        ParseRelation<ParseObject> relation = state.getRelation("condition");

        /*try {
            state.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        if (!pain.equals("")) {
            String painString = parseHandler.conditionMap.get(pain);
            condition = new ParseObject("PhysicalState");
            condition.setObjectId(painString);
            relation.add(condition);
        }
        if (!sleep.equals("")) {
            String sleepString = parseHandler.conditionMap.get(sleep);
            condition1 = new ParseObject("PhysicalState");
            condition1.setObjectId(sleepString);
            relation.add(condition1);
        }
        if (!mental.equals("")) {
            String mentalString = parseHandler.conditionMap.get(mental);
            condition2 = new ParseObject("PhysicalState");
            condition2.setObjectId(mentalString);
            relation.add(condition2);
            //condition.setObjectId("x11TfeRz6h");
        }

        try {
            state.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String moodClicked(View v) {
        ImageView imageView = (ImageView) v;
        //TextView moodLabel = (TextView) v;
        if (imageView == findViewById(R.id.sadLogo)){
            mood="Sad";
            Toast.makeText(getApplicationContext(), mood, Toast.LENGTH_SHORT).show();
        }else if(imageView == findViewById(R.id.normalLogo)){
            mood="Normal";
            Toast.makeText(getApplicationContext(), mood, Toast.LENGTH_SHORT).show();
        }else{
            mood="Happy";
            Toast.makeText(getApplicationContext(), mood, Toast.LENGTH_SHORT).show();
        }
        return mood;
    }

    public void painClicked(View v) {
        TextView painLabel = (TextView) v;
        if (flagConditions) {
            pain = painLabel.getText().toString();
            Toast.makeText(getApplicationContext(), pain, Toast.LENGTH_SHORT).show();
            flagConditions = false;
        } else {
            pain = "";
            Toast.makeText(getApplicationContext(), pain, Toast.LENGTH_SHORT).show();
            flagConditions = true;
        }
    }

    public void sleepClicked(View v) {
        TextView sleepLabel = (TextView) v;
        if (flagConditions) {
            sleep = sleepLabel.getText().toString();
            Toast.makeText(getApplicationContext(), sleep, Toast.LENGTH_SHORT).show();
            flagConditions = false;
        } else {
            sleep = "";
            Toast.makeText(getApplicationContext(), sleep, Toast.LENGTH_SHORT).show();
            flagConditions = true;
        }
    }

    public void mentalClicked(View v) {
        TextView mentalLabel = (TextView) v;
        if (flagConditions) {
            mental = mentalLabel.getText().toString();
            flagConditions = false;
        } else {
            mental = "";
            flagConditions = true;
        }
    }


    private Dialog generarDialogo(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(error);
        builder.setTitle("Atención");
        builder.setCancelable(true);
        return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logOut) {
            ParseUser.logOut();
            Intent intent = new Intent(MainActivity.this, Login_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        } else {
            Intent intent = new Intent(MainActivity.this, SetUpActivity.class);
            intent.putExtra("Flag A", 1);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkIfCurrentUser() {
        if (ParseUser.getCurrentUser() == null) {
            intent = new Intent(this, Login_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            //nameLabel.setText(ParseUser.getCurrentUser().get("Name").toString());
        }
    }

    public void checkIfFirstTime() {
        if (ParseUser.getCurrentUser().isNew()) {
            intent = new Intent(this, SetUpActivity.class);
            startActivity(intent);
        }
    }
}


/*try {
            //Obtiene objetos Persona creados por el User y hace where createdBy == usuario actual.
            ParseObject personaUser = ParseQuery.getQuery("User").whereEqualTo("createdBy",ParseUser.getCurrentUser()).getFirst();
            nameLabel.setText(personaUser.get("Name").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }*/



        /*ParseObject parseObject= new ParseObject("State");
        try {
            ParseObject parseObject1= ParseQuery.getQuery("Person").get("d");
        } catch (ParseException e) {
            e.printStackTrace();
            errorMessage=e.getMessage();
        }*/