package com.apkglobal.login;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class PopupActivity extends AppCompatActivity {
    private EditText inputEmail, inputMobile,inputotp,inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    RadioGroup radioGroup;
    RadioButton rb;
    Boolean b=true;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    String verificationcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        getSupportActionBar().hide();
        inputMobile=  (EditText)findViewById(R.id.mobile);
        inputotp=  (EditText)findViewById(R.id.otp);
        radioGroup=findViewById(R.id.radioc);
        radioGroup.clearCheck();
        auth = FirebaseAuth.getInstance();
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb=radioGroup.findViewById(i);
                if(i==R.id.seekerc){
                    //company_name.setVisibility(View.INVISIBLE);

                    b=true;
                   /* Toast.makeText(SignupActivity.this,"Seeker",Toast.LENGTH_LONG).show();*/
                }
                else if (i==R.id.companyc){
                  /*  Toast.makeText(SignupActivity.this,"Company",Toast.LENGTH_LONG).show();*/
                    // company_name.setVisibility(View.VISIBLE);

                    b=false;


                }

            }
        });





        mcallback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
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
                Toast.makeText(PopupActivity.this, "Code sent to this number", Toast.LENGTH_SHORT).show();
            }
        };



    }
    public void sendsms(View v){
        String number=inputMobile.getText().toString();
        if(!number.isEmpty()){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60, TimeUnit.SECONDS,this,mcallback);}
        else {inputMobile.setError(getString(R.string.enter_mobile));}
    }
    public void signInWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PopupActivity.this, "OTP Verified", Toast.LENGTH_SHORT).show();

                    if(b==true){
                    Intent i =new Intent(PopupActivity.this,MainActivity.class);
                    startActivity(i);}
                    else if(b==false){
                        startActivity(new Intent(PopupActivity.this, CompanyActivity.class));
                    }
                }
                else
                    inputotp.setError(getString(R.string.Invalid_Otp));

            }
        });
    }
    public void verify(View view){
        String input_code=inputotp.getText().toString();
        String mobile=inputMobile.getText().toString().trim();
        if (mobile.length()<10){
            inputMobile.setError(getString(R.string.enter_mobile));
                    return;
                }
        verifyPhoneNumber(verificationcode, input_code);


    }


    public void verifyPhoneNumber(String verificationcode, String input_code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationcode,input_code);
        signInWithPhone(credential);
    }

    @Override
    public void onBackPressed() {
               // super.onBackPressed();
    }
}

