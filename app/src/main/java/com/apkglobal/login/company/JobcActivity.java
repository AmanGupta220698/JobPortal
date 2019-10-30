package com.apkglobal.login.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.apkglobal.login.R;

public class JobcActivity extends AppCompatActivity {
    Button job1,job2,job3,job4,job5,job6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobc);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        job1=findViewById(R.id.job1);
        job2=findViewById(R.id.job2);
        job3=findViewById(R.id.job3);
        job4=findViewById(R.id.job4);
        job5=findViewById(R.id.job5);
        job6=findViewById(R.id.job6);
        getSupportActionBar().setTitle("JOBS");



        job1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent engineer=new Intent(JobcActivity.this,EngineercActivity.class);
                startActivity(engineer);
            }
        });




        job2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bpo=new Intent(JobcActivity.this,BpocActivity.class);
                startActivity(bpo);
            }
        });





        job3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hr=new Intent(JobcActivity.this,HrcActivity.class);
                startActivity(hr);
            }
        });






        job4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent account=new Intent(JobcActivity.this,AccountcActivity.class);
                startActivity(account);
            }
        });












        job5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(JobcActivity.this,ItcActivity.class);
                startActivity(it);
            }
        });


        job6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teacher=new Intent(JobcActivity.this,TeachercActivity.class);
                startActivity(teacher);
            }
        });



    }
}
