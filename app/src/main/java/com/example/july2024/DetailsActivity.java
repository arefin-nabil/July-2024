package com.example.july2024;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView marqueetxt, name, occupation, institute, deathdate, age, howdies, biography, placeofbirth;
    ImageView backbtn, image;
    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        marqueetxt = findViewById(R.id.marqueetxt);
        backbtn = findViewById(R.id.backbtn);
        name = findViewById(R.id.name);
        occupation = findViewById(R.id.occupation);
        institute = findViewById(R.id.institute);
        deathdate = findViewById(R.id.deathdate);
        image = findViewById(R.id.image);
        age = findViewById(R.id.age);
        howdies = findViewById(R.id.howdies);
        biography = findViewById(R.id.biography);
        placeofbirth = findViewById(R.id.placeofbirth);
        submitbtn = findViewById(R.id.submitbtn);


        marqueetxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marqueetxt.setSelected(true);


        // Get data from intent
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        occupation.setText(intent.getStringExtra("occupation"));
        institute.setText(intent.getStringExtra("institute"));
        deathdate.append(intent.getStringExtra("deathdate"));
        age.append(intent.getStringExtra("age"));
        howdies.setText(intent.getStringExtra("howdies"));
        biography.setText(intent.getStringExtra("biography"));
        placeofbirth.append(intent.getStringExtra("placeofbirth"));

        String imageUrl = intent.getStringExtra("image_url");


        Picasso.get()
                .load(imageUrl)
                .into(image);


        // =================send tect to another activity===================
        submitbtn.setText("Request to update the information of " + name.getText());
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, form.class);
                intent.putExtra("text_key", "Update info of " + name.getText());
                startActivity(intent);
            }
        });


        // Back button click listener
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
