package com.example.projekt_apki;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class SettingsActivity extends AppCompatActivity {
    private final int ID_HOME=1;
    private final int ID_GAME=2;
    private final int ID_ACHIEVENETS=3;
    private final int ID_ACCOUNT=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
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
                        Intent gameIntent = new Intent(SettingsActivity.this, GamesLobby.class);
                        startActivity(gameIntent);
                        break;
                    case ID_ACHIEVENETS:
                        Intent achievementsIntent = new Intent(SettingsActivity.this,Achievements.class);
                        startActivity(achievementsIntent);
                        break;

                    case ID_HOME:
                        Intent home = new Intent(SettingsActivity.this, MainActivity.class);
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
        bottomNavigation.show(ID_ACCOUNT,true);

    }

}