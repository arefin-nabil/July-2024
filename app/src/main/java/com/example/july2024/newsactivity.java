package com.example.july2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class newsactivity extends AppCompatActivity {
    TextView titeltext, detailtext, sourcetext;
    ImageView imageview, backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsactivity);

        titeltext = findViewById(R.id.titeltext);
        detailtext = findViewById(R.id.detailtext);
        sourcetext = findViewById(R.id.sourcetext);
        imageview = findViewById(R.id.imageview);
        backbtn = findViewById(R.id.backbtn);


        Intent intent = getIntent();
        int titleResId = intent.getIntExtra("titel_key", 0);
        int detailResId = intent.getIntExtra("detail_key", 0);
        int sourceResId = intent.getIntExtra("source_key", 0);

        String imageurl = intent.getStringExtra("image_key");

        Picasso.get()
                .load(imageurl)
                .placeholder(R.drawable.july)
                .into(imageview);



        if (titleResId != 0) {
            titeltext.setText(titleResId);
        }

        if (detailResId != 0) {
            detailtext.setText(detailResId);
        }
        if (sourceResId != 0) {
            sourcetext.setText(sourceResId);
        }


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}