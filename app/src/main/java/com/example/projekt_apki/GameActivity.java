package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    private Button btnUp, btnDown, btnLeft, btnRight;
    private View gameView;
    private View lineView;
    private int xPos, yPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        gameView = findViewById(R.id.gameView);
        lineView = findViewById(R.id.lineView);

        xPos = gameView.getLeft();
        yPos = gameView.getTop();

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveUp();
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveDown();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveLeft();
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveRight();
            }
        });

        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                float lineX = lineView.getX();
                float lineY = lineView.getY();

                if (x >= lineX && x <= (lineX + lineView.getWidth()) &&
                        y >= lineY && y <= (lineY + lineView.getHeight())) {
                    resetGame();
                }

                return true;
            }
        });
    }

    private void moveUp() {
        yPos -= 10;
        gameView.setY(yPos);
    }

    private void moveDown() {
        yPos += 10;
        gameView.setY(yPos);
    }

    private void moveLeft() {
        xPos -= 10;
        gameView.setX(xPos);
    }

    private void moveRight() {
        xPos += 10;
        gameView.setX(xPos);
    }

    private void resetGame() {
        xPos = gameView.getLeft();
        yPos = gameView.getTop();
        gameView.setX(xPos);
        gameView.setY(yPos);
    }
}
