package com.apkglobal.login;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class FetchActivity extends AppCompatActivity {
ListView listView;
int id =1;
SwipeRefreshLayout swipeRefreshLayout;
String url="http://searchkero.com/job_mitrc/ffetch.php";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);
        listView=findViewById(R.id.listview);
        swipeRefreshLayout=findViewById(R.id.swipe);

        fetchdata();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {


            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                fetchdata();
                swipeRefreshLayout.setRefreshing(false);

            }
        });




    }

    private void fetchdata() {


        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FetchActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.dobs,ParseJSON.names,ParseJSON.emails,ParseJSON.qualifications,ParseJSON.phones);
        listView.setAdapter(cl);
    }

    public void jobcclick(View view) {

        callruntime();}









    // Toast.makeText(EngineerActivity.this, "Applied Successfully", Toast.LENGTH_SHORT).show();







    private void callruntime() {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE},id);
        }
        else
        {
            Intent call =new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:7222860363"));
            startActivity(call);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==id){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                callruntime();
            }
        }
    }



}



