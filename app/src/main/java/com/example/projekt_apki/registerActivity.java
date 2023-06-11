package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

    EditText ed, ed2, ed3, ed4, ed5,ed6;
    DataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    public void RegisterClick (View v)
    {
        database = new DataBase(this);
        database.open();
        database.createTableOfUsers();
        ed=findViewById(R.id.name);
        ed2=findViewById(R.id.password);
        ed3=findViewById(R.id.wiek);
        ed4=findViewById(R.id.username);
        ed5=findViewById(R.id.plec);
        ed6=findViewById(R.id.passwordconfirm);

        String x, y, z, a, b,g;
        x=String.valueOf(ed.getText());
        y=String.valueOf(ed2.getText());
        z=String.valueOf(ed3.getText());
        a=String.valueOf(ed4.getText());
        b=String.valueOf(ed5.getText());
        g=String.valueOf(ed6.getText());
        if(y.equals(g)){
            database.insertData(a, y, x, b, Integer.parseInt(z), 5000 );
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "Account Created !", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Check if confirm password and password are the same", Toast.LENGTH_SHORT).show();
        }

    }
}

