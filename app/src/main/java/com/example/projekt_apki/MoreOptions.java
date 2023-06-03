package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MoreOptions extends AppCompatActivity {

    private EditText editText;
    private TextView textView2;
    int sumOfWater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_options);
        editText = findViewById(R.id.editTextNumber2);
        textView2 = findViewById(R.id.textView2);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                textView2.setText(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Nie używamy tej metody
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Nie używamy tej metody
            }

        });
    }
    public void plusClick(View v) {
        Intent i = new Intent(this, Water.class);
        String waterValueString = textView2.getText().toString();
        int waterValue = Integer.parseInt(waterValueString);
        i.putExtra("watervalue", waterValue);
        startActivity(i);
    }


}