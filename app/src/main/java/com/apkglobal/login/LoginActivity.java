package com.apkglobal.login;



import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    RadioGroup radioGroup;
    RadioButton rb;
    Boolean b;
    Boolean c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        // set the view now
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);

        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
       /* rb=findViewById(R.id.seeker);
        radioGroup=findViewById(R.id.radio);

        if(rb.isChecked()){
            b=true;
            c=true;
        }*/


        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


/*
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb=radioGroup.findViewById(i);
                if(i==R.id.seeker ){

                    //company_name.setVisibility(View.INVISIBLE);
                        b=true;
                        c=true;
                   *//* Toast.makeText(SignupActivity.this,"Seeker",Toast.LENGTH_LONG).show();*//*
                }
                else if (i==R.id.company){
                  *//*  Toast.makeText(SignupActivity.this,"Company",Toast.LENGTH_LONG).show();*//*
                    // company_name.setVisibility(View.VISIBLE);
                            b=false;
                            c=false;
                }

            }
        });*/




        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                 String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    inputEmail.setError(getString(R.string.Validemail));

                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError(getString(R.string.enter_password));
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(inputEmail.getText().toString(),inputPassword.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (inputPassword.getText().toString().length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                }
                                else {
                                    Intent intent=new Intent(LoginActivity.this,QuestionActivity.class);
                                   // intent.putExtra("Email", auth.getCurrentUser().getEmail());
                                    if(auth.getCurrentUser()!=null){
                                        Toast.makeText(LoginActivity.this, "userlogin", Toast.LENGTH_SHORT).show();
                                    }
                                    startActivity(intent);














                                }
                            }
                        });
            }
        });
    }
/*if(b==true){
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                                Bundle bundle=new Bundle();
                                                bundle.putString("key",email);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                                //finish();

 else if(b==false){
                                        Intent intentc = new Intent(LoginActivity.this, CompanyActivity.class);
                                        startActivity(intentc);
                                        finish();*/


}
