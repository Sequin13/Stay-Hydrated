package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Water extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        // Znajdź spinner za pomocą jego identyfikatora
        Spinner spinner = findViewById(R.id.spinner);

// Zdefiniuj adapter dla spinnera, który będzie dostarczał dane do wyświetlenia
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcje_spinnera, android.R.layout.simple_spinner_item);

// Określ styl wyglądu spinnera po rozwinięciu listy
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Przypisz adapter do spinnera
        spinner.setAdapter(adapter);

// Dodaj obsługę zdarzenia wyboru elementu z listy spinnera
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Kod do wykonania po wyborze elementu z listy spinnera
                String wybranaOpcja = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Wybrano: " + wybranaOpcja, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Kod do wykonania, gdy nie zostanie wybrany żaden element z listy spinnera
            }
        });

    }

}