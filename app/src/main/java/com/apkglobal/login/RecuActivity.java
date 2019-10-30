package com.apkglobal.login;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RecuActivity extends AppCompatActivity {

        int id=1;
    ListView joblistView;
    SwipeRefreshLayout swipeRefreshLayout;
    String url="http://searchkero.com/job_mitrc/frecfetch.php";
    TextView text_jobs;
    ImageView img_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Recruiters");


        joblistView=findViewById(R.id.reclistview);
        swipeRefreshLayout=findViewById(R.id.swipe);
        img_check=findViewById(R.id.img_check);
        text_jobs=findViewById(R.id.text_job);

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
                        Toast.makeText(RecuActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONrec pj = new ParseJSONrec(json);
        pj.parseJSONrec();
        Customlistrec cl = new Customlistrec(this, ParseJSONrec.company_names,ParseJSONrec.company_emails,
                ParseJSONrec.jobprofiles,ParseJSONrec.locations,ParseJSONrec.calls);
        joblistView.setAdapter(cl);
    }


    public void jobclick(View view) {

        callruntime();









        // Toast.makeText(EngineerActivity.this, "Applied Successfully", Toast.LENGTH_SHORT).show();






    }
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







