package com.apkglobal.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class EngineerActivity extends AppCompatActivity {


    ListView joblistView;
    SwipeRefreshLayout swipeRefreshLayout;
    String url="http://searchkero.com/job_mitrc/fjobfetch.php";
    TextView text_jobs;
    ImageView img_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Engineering Jobs");


            joblistView=findViewById(R.id.joblistview);
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
                        Toast.makeText(EngineerActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSONjob pj = new ParseJSONjob(json);
        pj.parseJSONjob();
        Customlistjob cl = new Customlistjob(this, ParseJSONjob.company_names,ParseJSONjob.company_emails,ParseJSONjob.experiences,
                ParseJSONjob.jobprofiles,ParseJSONjob.salarys,ParseJSONjob.locations);
        joblistView.setAdapter(cl);
    }


    public void jobclick(View view) {



        Snackbar.make(view, "APPLIED SUCCESSFLLY ", Snackbar.LENGTH_LONG).show();
        Toast.makeText(this, "Upload The Resume In image format If already Uploaded press back buttton ", Toast.LENGTH_LONG).show();
        Intent profile=new Intent(EngineerActivity.this,ResumeActivity.class);
        startActivity(profile);








       // Toast.makeText(EngineerActivity.this, "Applied Successfully", Toast.LENGTH_SHORT).show();






    }

}







