package com.apkglobal.login;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView work;
int id=1;
 TextView logint1;
 EditText username;
    private FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        work=findViewById(R.id.work);
        //username=findViewById(R.id.username);

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WorkActivity.class);
                startActivity(intent);
            }
        });


        //logint1.setText(getIntent().getExtras().getString("Email"));
        /*Bundle bundle=getIntent().getExtras();
        String  string=bundle.getString("key");*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        /*logint1=findViewById(R.id.loginemail);
        logint1.setText(string);*/

        setSupportActionBar(toolbar);



        /*appGetFirstTimeRun();
        if(appGetFirstTimeRun()!=1){
            Toast.makeText(this, "Question", Toast.LENGTH_SHORT).show();
            startActivity( new Intent(MainActivity.this,QuestionActivity.class));
            finish();
        }*/



        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent profile=new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(profile);
            return true;

        }
        else if(id == R.id.btn_signout){
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent prev=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(prev);
            return true;

        }
        else if(id==R.id.btn_exit){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setMessage("Are You Sure To Close The Application");
            alertDialog.setTitle("EXIT APPLICATION");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alertDialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            alertDialog.create();
            alertDialog.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            Intent home=new Intent(MainActivity.this,MainActivity.class);
            startActivity(home);
            return true;



        } else if (id == R.id.nav_jobs) {
            Intent job=new Intent(MainActivity.this,JobActivity.class);
            startActivity(job);
            return true;

        } else if (id == R.id.nav_recruiter) {
            Intent recu=new Intent(MainActivity.this,RecuActivity.class);
            startActivity(recu);
            return true;

        } else if (id == R.id.nav_setting) {
            Intent setting=new Intent(MainActivity.this,SettingActivity.class);
            startActivity(setting);
            return true;

        }


        else if (id == R.id.nav_share) {
            Intent share=new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT,"Click Link To Download The Job Portal\n"
                    + "https://play.google.com/store/apps/details?id=naukriApp.appModules.login&hl=en" );
            share.setType("text/plain");

            startActivity(Intent.createChooser(share,"Share Via"));
        } else if (id == R.id.nav_call) {
            callruntime();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callruntime() {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED)
        {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},id);
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


       /* private int appGetFirstTimeRun(){
            SharedPreferences appPreferences=getSharedPreferences("MYAPP",0);
            int appCurrentBuildVersion=BuildConfig.VERSION_CODE;
            int appLastBuildVersion =appPreferences.getInt("app_first_time",0);
            if(appLastBuildVersion==appCurrentBuildVersion){
                return 1;
            }
            else {appPreferences.edit().putInt("app_first_time",appCurrentBuildVersion).apply();
            if (appLastBuildVersion==0){
                return 0;}
                else
                {return 2;}
            }
    }

*/
}
