package com.example.projekt_apki;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Objects;

public class Water extends AppCompatActivity {
    public String wybranaOpcja;
    int sumOfWater = 0;
    TextView tv;
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

        tv = findViewById(R.id.howMuchWater);
        int ilosc = Integer.parseInt(tv.getText().toString());
        int sum = ilosc + waterValue;
        tv.setText(String.valueOf(sum));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wybranaOpcja = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Wybrano: " + wybranaOpcja, Toast.LENGTH_SHORT).show();
                if (Objects.equals(wybranaOpcja, "Więcej opcji"))
                    startActivity(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
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
}