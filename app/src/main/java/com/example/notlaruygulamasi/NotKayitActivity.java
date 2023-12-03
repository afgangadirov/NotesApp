package com.example.notlaruygulamasi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NotKayitActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextDers, editTextNot1, editTextNot2;
    private Button buttonKaydet;
    //private Veritabani vt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);

        toolbar = findViewById(R.id.toolbar);
        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);
        buttonKaydet = findViewById(R.id.buttonKaydet);

        toolbar.setTitle("Not Kayit");
        setSupportActionBar(toolbar);

        //vt = new Veritabani(this);

        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                if (ders_adi.isEmpty()){
                    Snackbar.make(view,"Ders adi giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (not1.isEmpty()){
                    Snackbar.make(view,"Not 1 giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (not2.isEmpty()){
                    Snackbar.make(view,"Not 2 giriniz",Snackbar.LENGTH_SHORT).show();
                    return;
                }



                //new NotlarDao().notEkle(vt,ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));

                notEkle(ders_adi,not1,not2);


                startActivity(new Intent(NotKayitActivity.this,MainActivity.class));
                finish();

            }
        });

    }


    public void notEkle(String ders_adi,String not1, String not2){

        String url = "http://kasimadalan.pe.hu/notlar/insert_not.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ders_adi",ders_adi);
                params.put("not1",not1);
                params.put("not2",not2);

                return params;
            }
        };

        Volley.newRequestQueue(NotKayitActivity.this).add(stringRequest);




    }

}