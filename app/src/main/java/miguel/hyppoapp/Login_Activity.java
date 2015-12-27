package miguel.hyppoapp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

import bolts.Continuation;
import bolts.Task;

public class Login_Activity extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
    }

    public void logearse(View v) {
        if(checkeoCampos()){
            progressBar.setVisibility(View.VISIBLE);
            ParseUser.logInInBackground(usernameField.getText().toString(), passwordField.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    if(e==null){
                        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        cleanEditText();
                    }else{
                        generarDialogo(e.getMessage()).show();

                    }
                }
            });
        }else{
            generarDialogo("Introduce los campos").show();

        }
    }





    public boolean checkeoCampos() {
        if (!usernameField.getText().toString().equals("") && !passwordField.getText().toString().equals(""))
            return true;
        return false;
    }


    private Dialog generarDialogo(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login_Activity.this);
        builder.setMessage(error);
        builder.setTitle("Atención");
        builder.setCancelable(true);
        return builder.create();
    }

    public void toSignUp(View v){
        Intent intent = new Intent(Login_Activity.this,SignUpActivity.class);
        startActivity(intent);
    }

    public void cleanEditText(){
        usernameField.setText("");
        passwordField.setText("");

    }

    public List<ParseObject> fetchDataLogin() {
        final ArrayList<ParseObject> listaEstados=new ArrayList<ParseObject>();
        ParseQuery<ParseObject> userQuery = ParseQuery.getQuery("State").whereEqualTo("Relation_User", ParseUser.getCurrentUser());
        try {
            userQuery.findInBackground().onSuccessTask(new Continuation<List<ParseObject>, Task<Void>>() {
                @Override
                public Task<Void> then(Task<List<ParseObject>> task) throws Exception {
                    List<ParseObject> results = task.getResult();
                    listaEstados.addAll(results);
                    return null;
                }
            }).waitForCompletion();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return listaEstados;
    }
}

