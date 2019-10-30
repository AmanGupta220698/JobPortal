package com.apkglobal.login.company;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.apkglobal.login.ProfileActivity;
import com.apkglobal.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EngineercActivity extends AppCompatActivity {

    EditText et_name,et_email,et_experience,et_salary,et_jobprofile,et_location;
    Button btn_save ;


    String sname,semail,sexperience,ssalary,sjobprofile,slocation;

    TextView t_date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineerc);


        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_experience=findViewById(R.id.et_experience);
        et_salary=findViewById(R.id.et_salary);
        et_jobprofile=findViewById(R.id.et_jobprofile);
        et_location=findViewById(R.id.et_location);
        btn_save=findViewById(R.id.btn_save);
        //btn_fetch=findViewById(R.id.btn_fetch);
        // s=inputemail.getText().toString().trim();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senddata();



                Toast.makeText(EngineercActivity.this, "Data Update", Toast.LENGTH_SHORT).show();


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
 /*   FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public FirebaseUser getUser() {
        return user;

    }*/

    private void senddata() {


        String url="http://searchkero.com/job_mitrc/fbpoinsert.php";

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
                sexperience=et_experience.getText().toString();

                ssalary=et_salary.getText().toString();
                sjobprofile=et_jobprofile.getText().toString();
                slocation=et_location.getText().toString();


                Map<String,String>map=new HashMap<>();
                map.put("companynameid",sname);
                map.put("companyemailid",semail);
                map.put("experienceid",sexperience);

                map.put("jobprofileid",sjobprofile);
                map.put("salaryid",ssalary);
                map.put("locationid",slocation);


                return map;



            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }




}

