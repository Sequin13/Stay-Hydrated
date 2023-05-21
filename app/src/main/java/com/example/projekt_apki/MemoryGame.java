package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.projekt_apki.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame extends AppCompatActivity {

    private List<Integer> images;
    private boolean isClicked;
    private ImageView lastClickedImage;
    private List<ImageView> matchedImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);

        images = getShuffledImages();
        isClicked = false;
        lastClickedImage = null;
        matchedImages = new ArrayList<>();
    }

    private List<Integer> getShuffledImages() {
        List<Integer> images = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            images.add(i);
            images.add(i);
        }
        Collections.shuffle(images);
        return images;
    }

    public void isWin(View view) {
        ImageView clickedImage = (ImageView) view;
        int imageIndex = getIndexOfImage(clickedImage);

        if (matchedImages.contains(clickedImage)) {
            // Kliknięty obrazek już został dopasowany
            return;
        }

        int imageResId = getImageResource(imageIndex);
        clickedImage.setImageResource(imageResId);

        if (!isClicked) {
            // Pierwsze kliknięcie
            isClicked = true;
            lastClickedImage = clickedImage;
        } else {
            // Drugie kliknięcie
            if (imageIndex == getIndexOfImage(lastClickedImage)) {
                // Odkryj oba pasujące obrazy
                clickedImage.setEnabled(false);
                lastClickedImage.setEnabled(false);
                matchedImages.add(clickedImage);
                matchedImages.add(lastClickedImage);

                if (matchedImages.size() == images.size()) {
                    // Wszystkie obrazki dopasowane, wykonaj odpowiednie działania
                    // np. wyświetl komunikat o wygranej
                }
            } else {
                // Odstaw obrazy do stanu początkowego po krótkim opóźnieniu
                final ImageView finalClickedImage = clickedImage;
                final ImageView finalLastClickedImage = lastClickedImage;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finalClickedImage.setImageResource(R.drawable.questionmark);
                        finalLastClickedImage.setImageResource(R.drawable.questionmark);
                    }
                }, 1000);
            }

            // Zresetuj flagi
            isClicked = false;
            lastClickedImage = null;
        }
    }

    private int getIndexOfImage(ImageView imageView) {
        // Znajdź indeks obrazka na podstawie ImageView
        ImageView[] imageViews = {
                findViewById(R.id.imageView1),
                findViewById(R.id.imageView2),
                findViewById(R.id.imageView3),
                findViewById(R.id.imageView4),
                findViewById(R.id.imageView5),
                findViewById(R.id.imageView6),
                findViewById(R.id.imageView7),
                findViewById(R.id.imageView8),
                findViewById(R.id.imageView12),
                findViewById(R.id.imageView9),
                findViewById(R.id.imageView10),
                findViewById(R.id.imageView11)
        };

        for (int i = 0; i < imageViews.length; i++) {
            if (imageView == imageViews[i]) {
                return i;
            }
        }
        return -1;
    }

    private int getImageResource(int imageIndex) {
        switch (images.get(imageIndex)) {
            case 1:
                return R.drawable.bottleofwater;
            case 2:
                return R.drawable.butlawody;
            case 3:
                return R.drawable.cupofwater;
            case 4:
                return R.drawable.mauzer;
            case 5:
                return R.drawable.waterdrop;
            case 6:
                return R.drawable.ocean;
            // Dodaj pozostałe przypadki dla innych obrazków
            default:
                return R.drawable.questionmark;
        }
    }
}
