package miguel.hyppoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private  Intent intent;
    private String errorMessage="";
    private TextView nameLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkIfCurrentUser();
        checkIfFirstTime();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameLabel =  (TextView)findViewById(R.id.tvUserName);
        try {
            //Obtiene objetos Persona creados por el User y hace where createdBy == usuario actual.
            ParseObject personaUser = ParseQuery.getQuery("Persona").whereEqualTo("createdBy",ParseUser.getCurrentUser()).getFirst();
            nameLabel.setText(personaUser.get("Name").toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }



        /*ParseObject parseObject= new ParseObject("State");
        try {
            ParseObject parseObject1= ParseQuery.getQuery("Person").get("d");
        } catch (ParseException e) {
            e.printStackTrace();
            errorMessage=e.getMessage();
        }*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addStates()){
                    //parseObject.put("");
                    //errorMessage=e.getMessage();
                }else{
                    generarDialogo(errorMessage).show();
                }
            }
        });



    }

    private boolean addStates() {

        return false;
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
            Intent intent = new Intent(MainActivity.this,Login_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }else {
            Intent intent = new Intent(MainActivity.this,SetUpActivity.class);
            intent.putExtra("Flag A", 1);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkIfCurrentUser(){
        if(ParseUser.getCurrentUser()==null){
            intent = new Intent(this, Login_Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void checkIfFirstTime(){
        if(ParseUser.getCurrentUser().isNew()){
            intent = new Intent(this,SetUpActivity.class);
            startActivity(intent);
        }
    }
}
