package com.example.july2024;

import static android.app.ProgressDialog.show;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;

public class form extends AppCompatActivity {
    TextView titeltext;
    EditText editTextDate, edname, edoccup, edinst, edage, edplace, edmartyr, edbio, edname2, edwhatsapp, edfb;
    Button submitbtn;
    ImageView backbtn;
    ProgressBar progressbar;

    TextView tvdate;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editTextDate = findViewById(R.id.editTextDate);
        submitbtn = findViewById(R.id.submitbtn);
        backbtn = findViewById(R.id.backbtn);
        titeltext = findViewById(R.id.titeltext);
        progressbar = findViewById(R.id.progressbar);

        edname = findViewById(R.id.edname);
        edoccup = findViewById(R.id.edoccup);
        edinst = findViewById(R.id.edinst);
        edage = findViewById(R.id.edage);
        edplace = findViewById(R.id.edplace);
        edmartyr = findViewById(R.id.edmartyr);
        edbio = findViewById(R.id.edbio);
        edname2 = findViewById(R.id.edname2);
        edwhatsapp = findViewById(R.id.edwhatsapp);
        edfb = findViewById(R.id.edfb);
        tvdate = findViewById(R.id.tvdate);
        checkbox = findViewById(R.id.checkbox);

        //==============change data by intent========================
        Intent intent = getIntent();
        String text = intent.getStringExtra("text_key");
        titeltext.setText(text);
        //==============date change by intent========================

        //==============date edit text========================
        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        //======================date edtt text ==================



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressbar.setVisibility(View.VISIBLE);


                if (edname.length() == 0 || edoccup.length() == 0 ||
                        edinst.length() == 0 || tvdate.length() == 0 ||
                        edage.length() == 0 || edplace.length() == 0 ||
                        edmartyr.length() == 0 || edbio.length() == 0 ||
                        edname2.length() == 0 || edwhatsapp.length() == 0 ||
                        edfb.length() == 0) {

                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(form.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!checkbox.isChecked()) {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(form.this, "Please check the checkbox", Toast.LENGTH_LONG).show();

                    return;
                }


                String name = edname.getText().toString();
                String occupation = edoccup.getText().toString();
                String institution = edinst.getText().toString();
                String date = tvdate.getText().toString();
                String age = edage.getText().toString();
                String place = edplace.getText().toString();
                String martyr = edmartyr.getText().toString();
                String biography = edbio.getText().toString();
                String name2 = edname2.getText().toString();
                String whatsapp = edwhatsapp.getText().toString();
                String facebook = edfb.getText().toString();




                RequestQueue queue = Volley.newRequestQueue(form.this);
                String url = "https://shopnojhuri.com/nabil/July2024/getdata.php?n="+ name
                        + "&o="+occupation
                        +"&i="+institution
                        +"&d="+date
                        +"&a="+age
                        +"&p="+place
                        +"&m="+martyr
                        +"&b="+biography
                        +"&nn="+name2
                        +"&w="+whatsapp
                        +"&f="+facebook;


                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                edname.setText("");
                                edoccup.setText("");
                                edinst.setText("");
                                editTextDate.setText("");
                                edage.setText("");
                                edplace.setText("");
                                edmartyr.setText("");
                                edbio.setText("");
                                edname2.setText("");
                                edwhatsapp.setText("");
                                edfb.setText("");
                                checkbox.setChecked(false);

                                progressbar.setVisibility(View.GONE);
                                showCustomDialog();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressbar.setVisibility(View.GONE);
                        Toast.makeText(form.this, "Please check your internet connection" , Toast.LENGTH_SHORT).show();

                    }
                });

                queue.add(stringRequest);

            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    ////// out of last 2nd bracket===========================

//=========method call=================datepicker================================
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                form.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        editTextDate.setText(selectedDate);
                        tvdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                },
                year, month, day);

        datePickerDialog.show();
//==========================datepicker================================
    }

//=======================alert dialog==============================
    private void showCustomDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog, null);


        TextView dialogTitle = dialogView.findViewById(R.id.titel);
        TextView dialogMessage = dialogView.findViewById(R.id.message);
        TextView dialogButton = dialogView.findViewById(R.id.okbtn);


        dialogTitle.setText("Submit success");
        dialogMessage.setText("We will verify and update the information very soon.\nThanks for being with us.\n");


        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setView(dialogView);
        androidx.appcompat.app.AlertDialog dialog = builder.create();


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Close the dialog
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB000000")));


        dialog.show();
    }
//=======================alert dialog==============================

}