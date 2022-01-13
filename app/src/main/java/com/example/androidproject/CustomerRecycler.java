package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomerRecycler extends AppCompatActivity {

    private static final String url = "http://192.168.1.115:80/mobileProject/get_items.php";
    private ArrayList<Room> roomList = new ArrayList<>();
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_recycler);

        Intent intent = getIntent();

        recycler = findViewById(R.id.customer_recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        loadItems();
    }

    private void loadItems() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {

                    try {
                        JSONArray array = new JSONArray(response);
                            System.out.println(array.toString());
                        for (int i = 0; i<array.length(); i++){
                            JSONObject roomObject = array.getJSONObject(i);
                           System.out.println("11111111111111");
                            int id = roomObject.getInt("roomID");
                            System.out.println("2222222222222222222222");
                            int capacity = roomObject.getInt("capacity");
                            System.out.println("333333333");
                            int priceByDay = roomObject.getInt("priceByDay");
                            System.out.println("444444444444");
                            String image = roomObject.getString("image");
                            Room room = new Room(id, capacity, priceByDay, image);
                            roomList.add(room);

                        }
                    }catch (Exception e){

                    }
                    RecyclerAdapter adapter = new RecyclerAdapter(CustomerRecycler.this,
                            roomList);
                    recycler.setAdapter(adapter);
                    for (int i = 0 ;i<roomList.size();i++){
                        System.out.println(roomList.get(i).toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CustomerRecycler.this, error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(CustomerRecycler.this).add(stringRequest);
    }
}