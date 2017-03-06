package com.trustinno.win.jobagtrustinno.Employer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.rey.material.widget.ProgressView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.datastore.employer;
import com.trustinno.win.jobagtrustinno.datastore.employerprofile;

import java.util.List;

import static com.trustinno.win.jobagtrustinno.Authentication.LoginActivity.token;
import static com.trustinno.win.jobagtrustinno.R.id.employer_email;

public class Employer extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private String  userupload_idget,user_id,employer_name,employer_id, employer_Name, employer_Username, employer_Userphone, employer_Useremail, employer_Useraddress, employer_Userpostal;
    String Token= LoginActivity.token;
    ConnectionHub communicator;
    ProgressView circularProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id
                .drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        communicator=new ConnectionHub();
        employer_id=getIntent().getExtras().getString("employerid");
        employer_name=getIntent().getExtras().getString("employername");
        employer_Useraddress=getIntent().getExtras().getString("employer_address");
        user_id =employer_id.toString();

        //        companypro(user_id,token);

        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,new Employer_company_profile());
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void uploadempprofile(String user_id,String token){

        communicator.employerprofile(user_id, token);

    }


    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {

        if (!serverEvent.getServerResponse().equals(null)) {
            if (!serverEvent.getServerResponse().getEmployerprofile().equals(null)){
            List<employer>employers=serverEvent.getServerResponse().getEmployerprofile();
            employer Employer=employers.get(0);
            userupload_idget=Employer.getempid().toString();
            employer_Name=Employer.getempname().toString();
            employer_Useraddress=Employer.getempaddress().toString();
            employer_Useremail=Employer.getempemail().toString();
            employer_Userphone=Employer.getempmobile().toString();
            employer_Userpostal=Employer.getPostal_code().toString();
            Bundle bundle=new Bundle();
            bundle.putString("employer_id",userupload_idget);
            bundle.putString("employer_name",employer_Name);
            bundle.putString("employer_address",employer_Useraddress);
            bundle.putString("employer_email",employer_Useremail);
            bundle.putString("employer_phone",employer_Userphone);
            bundle.putString("employer_postal",employer_Userpostal);
            Employer_profile empinfo=new Employer_profile();
            empinfo.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,empinfo);
            fragmentTransaction.commit();}
            else if (!serverEvent.getServerResponse().getEmployerprofiles().equals(null)){


            }

        }
    }

    @Override
    public void onResume() {

        super.onResume();
        BusProvider.getInstance().register(this);

    }

    @Override
    public void onPause() {

        super.onPause();
        BusProvider.getInstance().unregister(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void empupload(){
        uploadempprofile(user_id,token);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_employer_comprofile) {

            fragmentTransaction.replace(R.id.container,new Employer_company_profile());
            fragmentTransaction.commit();

        }

        else  if (id == R.id.nav_employer_jobupload) {
            fragmentTransaction.replace(R.id.container,new Employer_jobupload());
            fragmentTransaction.commit();


        } else if (id == R.id.nav_employercv) {




        } else if (id == R.id.nav_employer_profile) {
//            circularProgressBar = (ProgressView) findViewById(R.id.circular_progress);
            empupload();
        }

        else if (id == R.id.nav_employer_joblist)

        {


        }

        else if (id == R.id.nav_contactus) {



        } else if (id == R.id.nav_aboutus) {


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
