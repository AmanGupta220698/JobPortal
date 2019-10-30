package com.apkglobal.login;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.apkglobal.login.company.CompanyActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {

    EditText inputEmail,passwordc,  inputMobile,inputotp,inputPassword;
    Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    RadioGroup radioGroup;
    RadioButton rb;
    TextInputLayout c1;
    Boolean b,c;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        passwordc=(EditText) findViewById(R.id.cpassword);
       radioGroup=findViewById(R.id.radio);
       radioGroup.clearCheck();
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputMobile=  (EditText)findViewById(R.id.mobile);
        inputotp=  (EditText)findViewById(R.id.otp);
        inputEmail = (EditText) findViewById(R.id.email);
        c1=findViewById(R.id.c1);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);


        //company_name.setVisibility(View.INVISIBLE);
// Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb=radioGroup.findViewById(i);
                if(i==R.id.seeker){
                    //company_name.setVisibility(View.INVISIBLE);
                    c1.setVisibility(View.INVISIBLE);
                    b=true;

                   /* Toast.makeText(SignupActivity.this,"Seeker",Toast.LENGTH_LONG).show();*/
                }
                else if (i==R.id.company){
                  /*  Toast.makeText(SignupActivity.this,"Company",Toast.LENGTH_LONG).show();*/
                   // company_name.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    b=false;


                }

            }
        });





        /*mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {


            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationcode=s;
                Toast.makeText(SignupActivity.this, "Code sent to this number", Toast.LENGTH_SHORT).show();
            }
        };*/




      /*  btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });*/

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String cpassword=passwordc.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    inputEmail.setError(getString(R.string.Validemail));
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    inputPassword.setError(getString(R.string.enter_password));

                    return;
                }

                if (password.length() < 6) {
                    inputPassword.setError(getString(R.string.minimum_password));
                    return;
                }
                if(!password.equals(cpassword)){
                    passwordc.setError(getString(R.string.confirm));
                    return;
                }






               /* if (mobile.length()<10){
                    Toast.makeText(SignupActivity.this, "Enter 10 Digit Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {

                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else   {

                                        if(b==true){
                                    startActivity(new Intent(SignupActivity.this, PopupActivity.class));}
                                    else {
                                            if (b==false);
                                            //Toast.makeText(SignupActivity.this, "Company me click kr diya", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignupActivity.this, PopupActivity.class));
                                        }

                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }



/*
    public void sendsms(View v){
        String number=inputMobile.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,60, TimeUnit.SECONDS,this,mcallback
        );
    }
    public void signInWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "OTP Verified", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(SignupActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void verify(View view){
        String input_code=inputotp.getText().toString();

        verifyPhoneNumber(verificationcode, input_code);


    }

    public void verifyPhoneNumber(String verificationcode, String input_code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationcode,input_code);
        signInWithPhone(credential);
    }*/
}




