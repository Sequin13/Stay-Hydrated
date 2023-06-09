package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.projekt_apki.Achievements;
import com.example.projekt_apki.DataBase;
import com.example.projekt_apki.GamesLobby;
import com.example.projekt_apki.MoreOptions;
import com.example.projekt_apki.R;
import com.example.projekt_apki.SettingsActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final int ID_Water = 1;
    private final int ID_GAME = 2;
    private final int ID_ACHIEVENETS = 3;
    private final int ID_ACCOUNT = 4;
    private DataBase database;
    public String wybranaOpcja;
    int sumOfWater = 0;
    TextView tv, tv2, tv3Goal;
    int goal = 3000;
    private static final String CHANNEL_ID = "my_channel";

    public int idOfUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MeowBottomNavigation bottomNavigation = findViewById(R.id.bottomNavigation);

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
                    case ID_ACCOUNT:
                        Intent account = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(account);
                        break;
                }
            }
        }));
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                String name;
                switch (item.getId()) {
                    case ID_Water:
                        name = "Water";
                        break;
                    case ID_GAME:
                        name = "Games";
                        break;
                    case ID_ACHIEVENETS:
                        name = "Achievements";
                        break;
                    case ID_ACCOUNT:
                        name = "Account";
                        break;
                    default:
                        name = "";
                }
            }
        });
        bottomNavigation.setCount(ID_ACHIEVENETS, "4");
        bottomNavigation.show(ID_Water, true);

        database = new DataBase(this);
        database.open();
        database.createTableOfUsers();
        tv3Goal = findViewById(R.id.goal);
        database.deleteDatabase(this); //chwilowo

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcje_spinnera, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent i = new Intent(this, MoreOptions.class);
        int waterValue = getIntent().getIntExtra("watervalue", 0);

        tv2 = findViewById(R.id.howMuchWater2);
        tv2.setText("0");
        tv = findViewById(R.id.howMuchWater);
        int ilosc = 0;
        try {
            ilosc = Integer.parseInt(tv.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int sum = ilosc + waterValue;
        tv.setText(String.valueOf(sum));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wybranaOpcja = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Wybrano: " + wybranaOpcja, Toast.LENGTH_SHORT).show();
                if (Objects.equals(wybranaOpcja, "Więcej opcji")) {
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void plusOnClick(View v) {
        tv = findViewById(R.id.howMuchWater);
        sumOfWater = 0;
        try {
            sumOfWater = Integer.parseInt(tv.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        switch (wybranaOpcja) {
            case "Szklanka 200 ml":
                sumOfWater += 200;
                break;
            case "Kubek 300 ml":
                sumOfWater += 300;
                break;
            case "Butelka 500 ml":
                sumOfWater += 500;
                break;
            case "Butelka 750 ml":
                sumOfWater += 750;
                break;
            case "Butelka 1 l":
                sumOfWater += 1000;
                break;
            case "Butelka 1,5 l":
                sumOfWater += 1500;
                break;
            case "Butla 5 l":
                sumOfWater += 5000;
                break;
        }
        tv.setText(String.valueOf(sumOfWater));
        tv.invalidate();
    }

    public void resetClick(View v) {
        tv = findViewById(R.id.howMuchWater);
        tv2 = findViewById(R.id.howMuchWater2);
        tv.setText("0");
    }

    public void acceptClick(View v) throws InterruptedException {
        String temp;
        String temp2;
        tv = findViewById(R.id.howMuchWater);
        tv2 = findViewById(R.id.howMuchWater2);
        temp = tv.getText().toString();
        temp2 = tv2.getText().toString();

        temp = String.valueOf(Integer.parseInt(temp) + Integer.parseInt(temp2));

        tv.setText("0");
        tv2.setText(temp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Opis kanału powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_water_drop_24)
                .setContentTitle("Powiadomienie")
                .setContentText("Przykładowa treść powiadomienia")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(0, builder.build());
    }
}
