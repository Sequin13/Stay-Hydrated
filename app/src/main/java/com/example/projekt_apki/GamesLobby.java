package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GamesLobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_lobby);
    }

    public void memoryGameOnClick (View view)
    {
        Intent i = new Intent(this, MemoryGame.class);
        startActivity(i);
    }

    public void gameOnClick (View v)
    {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    public void clickerOnClick (View v)
    {
        Intent i = new Intent(this, clickerActivity.class);
        startActivity(i);
    }
}