package com.example.july2024;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class MainActivity2 extends AppCompatActivity {
    GridView gridView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    MainActivity2.myadapter adapter = new MainActivity2.myadapter(arrayList);
    TextView internettxt;
    SearchView searchView;
    LottieAnimationView Lottie;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gridView = findViewById(R.id.gridView);
        searchView = findViewById(R.id.searchView);
        internettxt = findViewById(R.id.internettxt);
        Lottie = findViewById(R.id.Lottie);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.VISIBLE);



        gridView.setAdapter(adapter);


//===========searchview=================
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                fileList(newText);
                return true;
            }
        });

//===========searchview=================

        // Fetch data
        fetchData();

    }

    private void fetchData() {

        String url = "https://shopnojhuri.com/nabil/July2024/shohidinfo.json";
        Toast.makeText(MainActivity2.this, "Loading data, please wait...", Toast.LENGTH_SHORT).show();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {
                            for (int x = 0; x < jsonArray.length(); x++) {

                                JSONObject jsnarray = jsonArray.getJSONObject(x);

                                Log.d("Sresponse", jsnarray.toString());

                                progressbar.setVisibility(View.GONE);

                                String image_url = jsnarray.getString("image");
                                String name = jsnarray.getString("name");
                                String occupation = jsnarray.getString("occupation");
                                String institute = jsnarray.getString("institute");
                                String deathdate = jsnarray.getString("deathdate");
                                String age = jsnarray.getString("age");
                                String howdies = jsnarray.getString("howdies");
                                String biography = jsnarray.getString("biography");
                                String placeofbirth = jsnarray.getString("placeofbirth");


                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("image_url", image_url);
                                hashMap.put("name", name);
                                hashMap.put("occupation", occupation);
                                hashMap.put("institute", institute);
                                hashMap.put("deathdate", deathdate);
                                hashMap.put("age", age);
                                hashMap.put("howdies", howdies);
                                hashMap.put("biography", biography);
                                hashMap.put("placeofbirth", placeofbirth);

                                arrayList.add(hashMap);

                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity2.this, "Database error.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Lottie.setVisibility(View.VISIBLE);
                        internettxt.setVisibility(View.VISIBLE);
                        gridView.setVisibility(View.GONE);
                        searchView.setVisibility(View.GONE);
                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity2.this, "Failed to load data.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(arrayRequest);
    }


    private class myadapter extends BaseAdapter {
        ArrayList<HashMap<String, String>> marrayList;
        public myadapter(ArrayList<HashMap<String, String>> arrayList) {
            this.marrayList = arrayList;
        }

        public void setFilteredList(ArrayList<HashMap<String, String>> filteredList) {
            this.marrayList = filteredList;
            notifyDataSetChanged();
        }



        @Override
        public int getCount() {
            return marrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return marrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater layinflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layinflate.inflate(R.layout.item, null);
            }

            ImageView image = convertView.findViewById(R.id.image);
            TextView name = convertView.findViewById(R.id.name);
            TextView occupation = convertView.findViewById(R.id.occupation);
            TextView institute = convertView.findViewById(R.id.institute);
            TextView deathdate = convertView.findViewById(R.id.deathdate);
            CardView cardbtn = convertView.findViewById(R.id.cardbtn);
            TextView serial = convertView.findViewById(R.id.serial);

            serial.setText(String.valueOf(position+1));

            HashMap<String, String> hashMap = marrayList.get(position);

            String image_url = hashMap.get("image_url");
            String nameText = hashMap.get("name");
            String occupationText = hashMap.get("occupation");
            String instituteText = hashMap.get("institute");
            String deathdateText = hashMap.get("deathdate");
            String ageText = hashMap.get("age");
            String howdiesText = hashMap.get("howdies");
            String biographyText = hashMap.get("biography");
            String placeofbirthText = hashMap.get("placeofbirth");

            // Set data to views
            name.setText(nameText);
            occupation.setText(occupationText);
            institute.setText(instituteText);
            deathdate.setText(deathdateText);

            Picasso.get().load(image_url)
                    .placeholder(R.drawable.july)
                    .into(image);


            cardbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity2.this, nameText, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this, DetailsActivity.class);
                    intent.putExtra("image_url", image_url);
                    intent.putExtra("name", nameText);
                    intent.putExtra("occupation", occupationText);
                    intent.putExtra("institute", instituteText);
                    intent.putExtra("deathdate", deathdateText);
                    intent.putExtra("age", ageText);
                    intent.putExtra("howdies", howdiesText);
                    intent.putExtra("biography", biographyText);
                    intent.putExtra("placeofbirth", placeofbirthText);


                    startActivity(intent);
                }
            });





            return convertView;
        }
    }


    private void fileList(String newText) {
        ArrayList<HashMap<String, String>> arrayList1 = new ArrayList<>();


        for (HashMap<String, String> detailsItem : arrayList) {
            String name = detailsItem.get("name").toLowerCase();
            String institute = detailsItem.get("institute").toLowerCase();
            String deathdate = detailsItem.get("deathdate").toLowerCase();

            if (name.contains(newText.toLowerCase()) ||
                    institute.contains(newText.toLowerCase()) ||
                    deathdate.contains(newText.toLowerCase())) {
                arrayList1.add(detailsItem);
            }
        }

        if (arrayList1.isEmpty()) {
            Toast.makeText(MainActivity2.this, "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setFilteredList(arrayList1);
        }
    }
}