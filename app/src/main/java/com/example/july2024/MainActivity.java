package com.example.july2024;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    CardView crd1, crd2, crd3, crd4, crd5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        crd1 = findViewById(R.id.crd1);
        crd2 = findViewById(R.id.crd2);
        crd3 = findViewById(R.id.crd3);
        crd4 = findViewById(R.id.crd4);
        crd5 = findViewById(R.id.crd5);


        crd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        crd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newsactivity.class);
                intent.putExtra("titel_key", R.string.injuredtitel);
                intent.putExtra("detail_key", R.string.injuredtext);
                intent.putExtra("image_key", "https://thehill.com/wp-content/uploads/sites/2/2024/08/bangladesh_student_protests_08122024_GettyImages-2165952517.jpg");
                intent.putExtra("source_key", R.string.injuredsource);
                startActivity(intent);
            }
        });


        crd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newsactivity.class);
                intent.putExtra("titel_key", R.string.arresttitel);
                intent.putExtra("detail_key", R.string.arresttext);
                intent.putExtra("image_key", "https://www.tbsnews.net/sites/default/files/styles/big_3/public/images/2021/06/18/arrest-tbs.jpg");
                intent.putExtra("source_key", R.string.arrestsource);
                startActivity(intent);
            }
        });


        crd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, form.class);
                intent.putExtra("text_key", "New MARTYRS info.");
                startActivity(intent);

            }
        });


        crd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showCustomDialog();


            }
        });


    }



    @Override
    public void onBackPressed() {
        showCustomDialog2();
    }

    private void showCustomDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog, null);

        TextView dialogTitle = dialogView.findViewById(R.id.titel);
        TextView dialogMessage = dialogView.findViewById(R.id.message);
        TextView dialogButton = dialogView.findViewById(R.id.okbtn);
        TextView message = dialogView.findViewById(R.id.message);

        dialogTitle.setText("Data Source and dev info.");
        dialogMessage.setText("All of the MARTYRS data has been collected from the website ' shohid.info '\n\nThis app is developed by Nabil.\nFor any queries, please contact: \narefinnabil256@gmail.com\n");
        dialogButton.setText("Ok");
        message.setTextSize(17f);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialogButton.setOnClickListener(v -> dialog.dismiss());

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB000000")));
        dialog.show();
    }

    private void showCustomDialog2() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog, null);

        TextView dialogTitle = dialogView.findViewById(R.id.titel);
        TextView dialogMessage = dialogView.findViewById(R.id.message);
        TextView dialogButtonYes = dialogView.findViewById(R.id.okbtn);
        TextView dialogButtonNo = dialogView.findViewById(R.id.okbtn2);

        dialogButtonNo.setVisibility(View.VISIBLE);

        dialogTitle.setText("Exit");
        dialogMessage.setText("Are you sure you want to exit?\n");
        dialogButtonYes.setText("Yes");
        dialogButtonNo.setText("No");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialogButtonYes.setOnClickListener(v -> finishAffinity());
        dialogButtonNo.setOnClickListener(v -> dialog.dismiss());

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB000000")));
        dialog.show();
    }


}
