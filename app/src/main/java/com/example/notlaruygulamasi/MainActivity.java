package com.example.notlaruygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Notlar> notlarArrayList;
    private NotlarAdapter adapter;
    //Veritabani vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //vt = new Veritabani(this);

       // notlarArrayList = new NotlarDao().tumNotlar(vt);

        /*double toplam = 0.0;

        for (Notlar n : notlarArrayList) {
            toplam = toplam + (n.getNot1()+n.getNot2())/2;
        }

        toolbar.setSubtitle("Ortalama : "+(toplam/notlarArrayList.size()));


        adapter = new NotlarAdapter(this,notlarArrayList);
        rv.setAdapter(adapter);*/

        tumNotlar();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NotKayitActivity.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




    public void tumNotlar(){
        String url = "http://kasimadalan.pe.hu/notlar/tum_notlar.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                double toplam = 0.0;

                notlarArrayList = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray notlar = jsonObject.getJSONArray("notlar");

                    for (int i = 0; i<notlar.length();i++){
                        JSONObject n = notlar.getJSONObject(i);

                        int not_id = n.getInt("not_id");
                        String ders_adi = n.getString("ders_adi");
                        int not1 = n.getInt("not1");
                        int not2 = n.getInt("not2");

                        Notlar not = new Notlar(not_id,ders_adi,not1,not2);

                        toplam = toplam + (not1 + not2)/2;

                        notlarArrayList.add(not);
                    }


                    adapter = new NotlarAdapter(MainActivity.this,notlarArrayList);
                    rv.setAdapter(adapter);

                    toolbar.setSubtitle("Ortalama : "+(toplam/notlarArrayList.size()));



                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);


    }





}