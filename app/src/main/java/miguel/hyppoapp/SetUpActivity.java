package miguel.hyppoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

public class SetUpActivity extends AppCompatActivity {
    private EditText nameField, genreField, DoBField, ToHField;
    Intent intent= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        intent= getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameField = (EditText) findViewById(R.id.nameField);
        genreField = (EditText) findViewById(R.id.genreField);
        DoBField = (EditText) findViewById(R.id.DoBField);
        ToHField = (EditText) findViewById(R.id.ToHField);


        if (intent != null) {
            Toast.makeText(SetUpActivity.this, "Llega Intent", Toast.LENGTH_SHORT).show();
            if (intent.getExtras().getInt("Flag A") == 1) {
                ParseQuery<ParseObject> gameQuery = ParseQuery.getQuery("Persona");
                try {
                    ParseObject persona = gameQuery.whereEqualTo("createdBy", ParseUser.getCurrentUser()).getFirst();
                    nameField.setText(persona.getString("Name"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                nameField.setText(ParseUser.getCurrentUser().getString("Name"));
            }
            int flagIntent = intent.getExtras().getInt("Flag A");
            Log.v("Flag", String.valueOf(flagIntent));
            //Toast.makeText(SetUpActivity.this,flagIntent, Toast.LENGTH_SHORT).show();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Flag i" , String.valueOf(intent.getExtras().getInt("Flag A")));
                if (intent.getExtras().getInt("Flag A") == 0) {
                    insertPerson(view);
                    Toast.makeText(SetUpActivity.this, "0", Toast.LENGTH_SHORT).show();
                    intent = new Intent(SetUpActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    insertPerson(view);
                    finish();
                }
            }
        });
    }

    public void insertPerson(View v) {
        ParseObject person = new ParseObject("Persona");
        //As follows, the Person is linked to one User.
        person.put("createdBy", ParseUser.getCurrentUser());
        person.put("Name", nameField.getText().toString());
        person.put("Relation_Genre", genreField.getText().toString());
        person.put("DoB", DoBField.getText().toString());
        person.put("TypeOfHypo", ToHField.getText().toString());
        person.saveInBackground();

    }

}
