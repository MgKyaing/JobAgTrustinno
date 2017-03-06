package com.trustinno.win.jobagtrustinno.Employer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.datastore.employer;

import java.util.List;

/**
 * Created by zarni on 3/1/17.
 */

public class Employer_posthub extends Employer{

    private String userupload_idget, user_id,employer_id, employer_Name, employer_Username, employer_Userphone, employer_Useremail, employer_Useraddress, employer_Userpostal;
    String token = LoginActivity.token;
    ConnectionHub communicator;
    public Employer_posthub()
    {
            communicator=new ConnectionHub();
        companypro(user_id,token);
    }

    public void companypro(String user_id,String token){
        communicator.employerprofile(user_id,token);
    }

    public void onServerEvent(ServerEvent serverEvent){
        if (serverEvent.getServerResponse()!=null)

        {
            List<employer> employers=serverEvent.getServerResponse().getEmployerprofile();
            employer Employer=employers.get(0);
            userupload_idget=Employer.getempid().toString();
            employer_Name=Employer.getempname().toString();
            employer_Useraddress=Employer.getempaddress().toString();
            employer_Useremail=Employer.getempemail().toString();
            employer_Userphone=Employer.getempmobile().toString();
            employer_Userpostal=Employer.getPostal_code().toString();
            Bundle bundle=new Bundle();
            bundle.putString("employer_id",employer_id);
            bundle.putString("employer_name",employer_Username);
            bundle.putString("employer_address",employer_Useraddress);
            Employer_profile empinfo=new Employer_profile();
            empinfo.setArguments(bundle);
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,empinfo);
            fragmentTransaction.commit();

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

}

