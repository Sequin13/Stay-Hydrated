package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout layoutData;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        layoutData = findViewById(R.id.layout_data);
        sessionManager = SessionManager.getInstance();

        String loggedInUser = sessionManager.getLoggedInUser();

        DataBase database = new DataBase(this);
        database.open();
        database.printCurrentlyData(loggedInUser);
        database.close();
    }

    public void addDataToLayout(String data) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(data);

        layoutData.addView(textView);
    }

    public void ClearDataOnClick(View v)
    {
        DataBase database = new DataBase(this);
        database.open();
        database.deleteTableOfUsers();
        database.close();
    }
}
