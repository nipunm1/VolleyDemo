package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.volleydemo.model.Jsontojava;
import com.example.volleydemo.model.Sirfollowers;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView textView;
TextView text,text2;
RecyclerView recyclerView;
ImageView img;
List<Sirfollowers> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView = findViewById(R.id.text);
        //text = findViewById(R.id.textview);
        //text2 = findViewById(R.id.textview2);
        //img = findViewById(R.id.imageview);
        //callVolley();
        //calljsonVolley();
        //calljosnwithgsonnjavaclass();
        recyclerView = findViewById(R.id.recyclerview);
        getSirFollowers();
    }
    public void getSirFollowers(){
        String url = "https://api.github.com/users/cmpundhir/followers";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                list = gson.fromJson(response, list.getClass());
                //Log.d("List1", list.toString());
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(MainActivity.this, list);
                recyclerView.setAdapter(recyclerAdapter);
                //Log.d("activity", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error : "+error,Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    /*public void calljosnwithgsonnjavaclass(){
        String url = "https://api.github.com/users/nipunm1";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Jsontojava jsontojav = gson.fromJson(response, Jsontojava.class);
                text.setText(jsontojav.getLogin());
                text2.setText(jsontojav.getId()+"");
                Glide.with(MainActivity.this).load(jsontojav.getAvatarUrl()).into(img);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text.setText("error : "+error);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    public void callVolley(){
        String url = "https://github.com/";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error : "+error);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    public void calljsonVolley(){
        String url = "https://api.github.com/users/nipunm1";
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String username = response.getString("login");
                    text.setText(username);
                    long userid = response.getLong("id");
                    text2.setText(userid+"");
                    String img1 = response.getString("avatar_url");
                    Glide.with(MainActivity.this).load(img1).into(img);
                    //textView.setText(response.toString());
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                text.setText("Error : "+error);
                //textView.setText("Error : "+error);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }*/
}
