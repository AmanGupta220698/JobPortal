package com.apkglobal.login;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.apkglobal.login.company.CompanyActivity;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button submit;
    RadioButton rb;
    Boolean b;
    Boolean c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        submit=findViewById(R.id.btn_submit);
        rb=findViewById(R.id.seeker);
        radioGroup=findViewById(R.id.radio);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 15) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        if(rb.isChecked()){
            b=true;
            c=true;
        }



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb=radioGroup.findViewById(i);
                if(i==R.id.seeker ){

                    //company_name.setVisibility(View.INVISIBLE);
                    b=true;
                    c=true;
                   /* Toast.makeText(SignupActivity.this,"Seeker",Toast.LENGTH_LONG).show();*/
                }
                else if (i==R.id.company){
                  /*  Toast.makeText(SignupActivity.this,"Company",Toast.LENGTH_LONG).show();*/
                    // company_name.setVisibility(View.VISIBLE);
                    b=false;
                    c=false;
                }

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(b==true){
                    Toast.makeText(QuestionActivity.this, "You Are Seeker", Toast.LENGTH_SHORT).show();
                    Intent seeker=new Intent(QuestionActivity.this,MainActivity.class);
                    startActivity(seeker);
                    finish();
                }
                else if(b==false){
                    Toast.makeText(QuestionActivity.this, "You Are Company", Toast.LENGTH_SHORT).show();
                    Intent company=new Intent(QuestionActivity.this, CompanyActivity.class);
                    startActivity(company);
                    finish();

                }
            }
        });
    }
}
