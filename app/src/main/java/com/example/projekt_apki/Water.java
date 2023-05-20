package com.example.projekt_apki;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projekt_apki.MoreOptions;
import com.example.projekt_apki.R;

import java.util.Objects;

public class Water extends AppCompatActivity {
    public String wybranaOpcja;
    int sumOfWater = 0;
    TextView tv, tv2, tv3Goal;
    int goal = 3000;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

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
        int ilosc = Integer.parseInt(tv.getText().toString());
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

        tv3Goal = findViewById(R.id.goal);
        tv3Goal.setText("3000"); // chwilowo
    }
    @SuppressLint("SetTextI18n")
    public void plusOnClick(View v) {
        tv = findViewById(R.id.howMuchWater);
        sumOfWater = Integer.parseInt(tv.getText().toString());
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
    public void resetClick(View v)
    {
        tv=findViewById(R.id.howMuchWater);
        tv2=findViewById(R.id.howMuchWater2);
        tv.setText("0");
    }
    public void acceptClick(View v) throws InterruptedException {
        String temp;
        String temp2;
        tv=findViewById(R.id.howMuchWater);
        tv2=findViewById(R.id.howMuchWater2);
        temp = tv.getText().toString();
        temp2=tv2.getText().toString();

        temp=String.valueOf(Integer.parseInt(temp)+Integer.parseInt(temp2));

        tv.setText("0");
        tv2.setText(temp);
    }



}