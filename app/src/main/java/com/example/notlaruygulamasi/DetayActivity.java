package com.example.notlaruygulamasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class DetayActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextDers, editTextNot1, editTextNot2;
    //private Veritabani vt;
    private Notlar not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        toolbar = findViewById(R.id.toolbar);
        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);

        not = (Notlar) getIntent().getSerializableExtra("nesne");


        toolbar.setTitle("Not Detay");
        setSupportActionBar(toolbar);

        //vt = new Veritabani(this);

        editTextDers.setText(not.getDers_adi());
        editTextNot1.setText(String.valueOf(not.getNot1()));
        editTextNot2.setText(String.valueOf(not.getNot2()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbar,"Silinsin mi?",Snackbar.LENGTH_SHORT).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //new NotlarDao().notSil(vt,not.getNot_id());

                        notSil(not.getNot_id());

                        startActivity(new Intent(DetayActivity.this,MainActivity.class));
                        finish();

                    }
                }).show();
                return true;
            case R.id.action_duzenle:

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();

                if (ders_adi.isEmpty()){
                    Snackbar.make(toolbar,"Ders adi giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;
                }
                if (not1.isEmpty()){
                    Snackbar.make(toolbar,"Not 1 giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;
                }
                if (not2.isEmpty()){
                    Snackbar.make(toolbar,"Not 2 giriniz",Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                //new NotlarDao().notDuzenle(vt,not.getNot_id(),ders_adi,Integer.parseInt(not1),Integer.parseInt(not2));


                notGuncelle(not.getNot_id(),editTextDers.getText().toString(),editTextNot1.getText().toString(),editTextNot2.getText().toString());



                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                finish();
                return true;
            default:
                return false;
        }
    }



    public void notSil(int not_id){

        String url = "http://kasimadalan.pe.hu/notlar/delete_not.php";

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
                params.put("not_id",String.valueOf(not_id));
                return params;
            }
        };

        Volley.newRequestQueue(DetayActivity.this).add(stringRequest);

    }




    public void notGuncelle(int not_id,String ders_adi,String not1, String not2){

        String url = "http://kasimadalan.pe.hu/notlar/update_not.php";

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
                params.put("not_id",String.valueOf(not_id));
                params.put("ders_adi",ders_adi);
                params.put("not1",not1);
                params.put("not2",not2);
                return params;
            }
        };

        Volley.newRequestQueue(DetayActivity.this).add(stringRequest);

    }

}