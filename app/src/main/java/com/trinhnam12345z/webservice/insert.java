package com.trinhnam12345z.webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class insert extends AppCompatActivity {

    EditText editTenNV,editSDT;
    Button btnInsert;
    String urlInsert = "http://trinhnam12345z-001-site1.ftempurl.com/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editTenNV = (EditText) findViewById(R.id.editTenNV);
        editSDT = (EditText) findViewById(R.id.editSDT);
        btnInsert = (Button) findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themNV(urlInsert);
            }
        });

    }

    private void themNV(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("insert data successful")){
                            Toast.makeText(insert.this,"Thêm thành công !",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(insert.this,MainActivity.class));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(insert.this, "Xảy ra lỗi!", Toast.LENGTH_LONG).show();
                        Log.d("Thông báo", "Lỗi \n"+ error.toString());
                    }
                })
        {
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("TenNV",editTenNV.getText().toString());
                params.put("SDT",editSDT.getText().toString());
                return params;
            }
         };
        requestQueue.add(stringRequest);
    }
}