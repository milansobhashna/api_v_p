package com.example.api_v_p;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://mutable-villages.000webhostapp.com/test_post.php";
    ;
    RecyclerView rv1;
    private List<personlist> list;
    Button btn;
    EditText et;
    String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        btn = findViewById(R.id.btn1);
        et = findViewById(R.id.et1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = et.getText().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        PersonRespose personRespose = new Gson().fromJson(response, PersonRespose.class);
                        list = personRespose.user;

//                            String res = new Gson().toJson(personRespose, PersonRespose.class);




                            /*JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("user");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject juser = jsonArray.getJSONObject(i);
                                personlist personlist1 = new personlist();
                                personlist1.setName(juser.optString("name", ""));
                                personlist1.setEmail(juser.optString("email", ""));
                                list.add(personlist1);
                            }*/
                        Log.d("data get", String.valueOf(list));
                        rv1 = findViewById(R.id.rv1);
                        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list);
                        rv1.setHasFixedSize(true);
                        rv1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv1.setAdapter(recyclerViewAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("new", String.valueOf(error));
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("u_name", s);
                        return MyData;
                    }
                };
                requestQueue.add(jsonObjectRequest);


            }
        });
    }
}
