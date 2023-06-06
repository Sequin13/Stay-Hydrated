package com.example.projekt_apki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashscreen extends AppCompatActivity {
    private  static int DELAY_TIME=4000;

    Animation topAnim,bottomAnim;
    ImageView imageView;
    TextView app_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imageView=findViewById(R.id.image2);
        app_name=findViewById(R.id.app_name);

        imageView.setAnimation(topAnim);
        app_name.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(splashscreen.this,LoginActivity.class);
                Pair[]pairs = new Pair[2];
                pairs[0]=new Pair(imageView,"splash_image");
                pairs[1]=new Pair(imageView,"splash_text");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(splashscreen.this,pairs);
                startActivity(i,options.toBundle());
            }
        },DELAY_TIME);
    }
}