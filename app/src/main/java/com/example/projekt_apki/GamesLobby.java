package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class GamesLobby extends AppCompatActivity {
    private final int ID_HOME=1;
    private final int ID_GAME=2;
    private final int ID_ACHIEVENETS=3;
    private final int ID_ACCOUNT=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_lobby);
        MeowBottomNavigation bottomNavigation=findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.ic_baseline_water_drop_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_GAME, R.drawable.game));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACHIEVENETS, R.drawable.achievements));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.account));
        bottomNavigation.setOnClickMenuListener((new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case ID_GAME:
                        Intent gameIntent = new Intent(GamesLobby.this, GamesLobby.class);
                        startActivity(gameIntent);
                        break;
                    case ID_ACHIEVENETS:
                        Intent achievementsIntent = new Intent(GamesLobby.this, Achievements.class);
                        startActivity(achievementsIntent);
                        break;

                    case ID_HOME:
                        Intent home = new Intent(GamesLobby.this, MainActivity.class);
                        startActivity(home);
                        break;
                }
            }
        }));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name;
                switch (item.getId()){
                    case ID_HOME:name="Home";
                        break;
                    case ID_GAME:name="Games";
                        break;
                    case ID_ACHIEVENETS:name="Achievements";
                        break;
                    case ID_ACCOUNT:name="Account";
                        break;
                    default: name="";
                }
            }
        });
        bottomNavigation.setCount(ID_ACHIEVENETS,"4");
        bottomNavigation.show(ID_GAME,true);
    }

    public void memoryGameOnClick (View view)
    {
        Intent i = new Intent(this, MemoryGame.class);
        startActivity(i);
    }
    public void labiryntOnClick (View view)
    {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }
    public void clickerOnClick (View view)
    {
        Intent i = new Intent(this, clickerActivity.class);
        startActivity(i);
    }



}