package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
   private final int ID_Water=1;
   private final int ID_GAME=2;
   private final int ID_ACHIEVENETS=3;
   private final int ID_ACCOUNT=4;
   private DataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeowBottomNavigation bottomNavigation=findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_Water, R.drawable.ic_baseline_water_drop_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_GAME, R.drawable.game));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACHIEVENETS, R.drawable.achievements));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.sett));

        bottomNavigation.setOnClickMenuListener((new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case ID_GAME:
                        Intent gameIntent = new Intent(MainActivity.this, GamesLobby.class);
                        startActivity(gameIntent);
                        break;
                    case ID_ACHIEVENETS:
                        Intent achievementsIntent = new Intent(MainActivity.this, Achievements.class);
                        startActivity(achievementsIntent);
                        break;
                }
            }
        }));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name;
                switch (item.getId()){
                    case ID_Water:name="Water";
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
        bottomNavigation.show(ID_Water,true);


        //{
        database = new DataBase(this);
        database.open();
        database.createTableOfUsers();
        database.insertData("Marek","Czekajski", "M", 48, 3000);
        database.insertData("Agata","Kudła", "K",20, 2000);
        database.printData();
        database.deleteDatabase(this); //chwilowo
        //} - chwilowo -  testowanie bazy danych

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }


    public void waterOnClick (View view)
    {
        Intent i = new Intent(this, Water.class);
        startActivity(i);
    }
    public void gamesOnClick (View view)
    {
        Intent i = new Intent(this, GamesLobby.class);
        startActivity(i);
    }
}