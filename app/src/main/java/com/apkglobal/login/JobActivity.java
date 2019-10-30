package com.apkglobal.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class JobActivity extends AppCompatActivity {
Button job1,job2,job3,job4,job5,job6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        job1=findViewById(R.id.job1);
        job2=findViewById(R.id.job2);
        job3=findViewById(R.id.job3);
        job4=findViewById(R.id.job4);
        job5=findViewById(R.id.job5);
        job6=findViewById(R.id.job6);

        job1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent engineer=new Intent(JobActivity.this,EngineerActivity.class);
                startActivity(engineer);
            }
        });




        job2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bpo=new Intent(JobActivity.this,BpoActivity.class);
                startActivity(bpo);
            }
        });





        job3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hr=new Intent(JobActivity.this,HrActivity.class);
                startActivity(hr);
            }
        });






        job4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent account=new Intent(JobActivity.this,AccountActivity.class);
                startActivity(account);
            }
        });












        job5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(JobActivity.this,ITActivity.class);
                startActivity(it);
            }
        });


        job6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent teacher=new Intent(JobActivity.this,TeacherActivity.class);
                startActivity(teacher);
            }
        });



    }
}
