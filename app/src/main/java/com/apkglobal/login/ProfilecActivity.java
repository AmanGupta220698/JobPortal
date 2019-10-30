package com.apkglobal.login;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ProfilecActivity extends AppCompatActivity {
    EditText et_name,et_email,inputemail,et_dob,et_qualification,et_phone;
    Button btn_save ;
    private static final int SELECT_PICTURE = 0;
    private ImageView imageView;

    String sname,s,semail,sdob,squalification,sphone;

    TextView t_date;
    Calendar mCurrentdate;
    int day,month,year;

    int pid=1;
    DatePickerDialog.OnDateSetListener  onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilec);


        et_name=findViewById(R.id.et_namec);
        et_email=findViewById(R.id.et_emailc);

        et_dob=findViewById(R.id.et_datec);
        et_qualification=findViewById(R.id.et_qualification);
        btn_save=findViewById(R.id.btn_save);
        et_phone=findViewById(R.id.phonec);
        //btn_fetch=findViewById(R.id.btn_fetch);
        inputemail=findViewById(R.id.et_emailc);
      // s=inputemail.getText().toString().trim();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senddata();



                Toast.makeText(ProfilecActivity.this, "Profile Update", Toast.LENGTH_SHORT).show();


            }
        });
       /* btn_fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent fetch=new Intent(ProfileActivity.this,FetchActivity.class);
                startActivity(fetch);
            }
        });
*/

/*        mCurrentdate=Calendar.getInstance();


        day=mCurrentdate.get(Calendar.DAY_OF_MONTH);
        month=mCurrentdate.get(Calendar.MONTH);
        year=mCurrentdate.get(Calendar.YEAR);
        month=month+1;
        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog=new DatePickerDialog(ProfilecActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month=month+1;
                                et_dob.setText(year+"/"+month+"/"+day);


                            }
                        },year,month,day);
                dialog.show();

            }
        });*/

            /*t_date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal=Calendar.getInstance();
                    int year=cal.get(Calendar.YEAR);
                    int month=cal.get(Calendar.MONTH);
                    int day=cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog=new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            onDateSetListener,year,month,day);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                }
            });
                onDateSetListener=new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month=month+1;
                        Log.d(TAG, "onDateSet: yyyy/mm/dd:"+year+"/"+month+"/"+day);
                        String date=year+"/"+month+"/"+day;
                        t_date.setText(date);
                    }
                };
*/

    }
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public FirebaseUser getUser() {
        return user;

    }

    private void senddata() {


        String url="http://searchkero.com/job_mitrc/frecinsert.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                sname=et_name.getText().toString();
                semail=et_email.getText().toString();
                sdob=et_dob.getText().toString();

                squalification=et_qualification.getText().toString();
                sphone=et_phone.getText().toString();


                Map<String,String>map=new HashMap<>();
                map.put("companynameid",sname);
                map.put("companuemailid",semail);
                map.put("jobprofileid",sdob);
                map.put("locationid",squalification);
                map.put("companycallid",sphone);


                return map;



            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }




}

