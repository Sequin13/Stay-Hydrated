package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class MainActivity extends AppCompatActivity {
   private final int ID_HOME=1;
   private final int ID_GAME=2;
   private final int ID_ACHIEVENETS=3;
   private final int ID_ACCOUNT=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MeowBottomNavigation bottomNavigation=findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(ID_HOME, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_GAME, R.drawable.game));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACHIEVENETS, R.drawable.achievements));
        bottomNavigation.add(new MeowBottomNavigation.Model(ID_ACCOUNT, R.drawable.account));

        bottomNavigation.setOnClickMenuListener((new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
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
        bottomNavigation.setCount(ID_ACHIEVENETS,"3");
        bottomNavigation.show(ID_HOME,true);
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