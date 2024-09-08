package com.example.july2024;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class splash_screen extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize views
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        lottieAnimationView = findViewById(R.id.Lottie);

        textView1.animate().translationY(-1000).setDuration(4000).setStartDelay(4000);
        textView2.animate().translationY(2000).setDuration(4000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(4000).setStartDelay(4000);

// Start MainActivity after the animations finish
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash_screen.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the splash screen activity
        }, 3500);
    }
}
