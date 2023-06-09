package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText ed, ed2;
    DataBase database;

    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new DataBase(this);
        database.open();

        ed = findViewById(R.id.userName);
        ed2 = findViewById(R.id.haselko);
    }

    public void ButtonOnClick(View view) {
        String user = ed.getText().toString();
        String password = ed2.getText().toString();

        if (database.getAccess(user, password)) {
            int userId = database.getUserId(user);
            SessionManager.getInstance().setLoggedInUser(user);

            int userGoal = database.getUserGoal(userId);



            SessionManager.getInstance().setUserGoal(userGoal);

            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Błędne dane logowania", Toast.LENGTH_SHORT).show();
        }
    }

    public void RegisterOnClick(View v) {
        Intent i = new Intent(this, registerActivity.class);
        startActivity(i);
    }
}
