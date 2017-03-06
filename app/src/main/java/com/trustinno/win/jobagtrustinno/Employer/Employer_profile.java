package com.trustinno.win.jobagtrustinno.Employer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.datastore.employer;
import com.trustinno.win.jobagtrustinno.datastore.employerprofile;
import com.trustinno.win.jobagtrustinno.datastore.empproput;

import java.util.List;


public class Employer_profile extends Fragment{

    private String  user_id,employer_id,employer_Name, employer_Username, employer_Userphone, employer_Useremail, employer_Useraddress, employer_Userpostal;
    private EditText employer_name,employer_phone,employer_email,employer_address,employer_postal;
    String token =LoginActivity.token;
    ConnectionHub communicator;
    String name,mobile_no,email,address,postal_code;
    int township,city,country;
    private String id;
    public String userupload_idget,userupload_idpost;
    public Employer_profile() {
        communicator=new ConnectionHub();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {

        View rootView=inflater.inflate(R.layout.fragment_employerprofile, container, false);

        employer_id = this.getArguments().getString("employer_id");
        employer_Name = this.getArguments().getString("employer_name");
        employer_Useraddress=this.getArguments().getString("employer_address");
        employer_Useremail=this.getArguments().getString("employer_email");
        employer_Userpostal=this.getArguments().getString("employer_postal");
        employer_Userphone=this.getArguments().getString("employer_phone");
        id =employer_id.toString();
        Button editbutton=(Button)rootView.findViewById(R.id.employer_profile_upload);
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employer_name=(EditText)getView().findViewById(R.id.employer_name);
                employer_phone=(EditText)getView().findViewById(R.id.employer_phone);
                employer_address=(EditText)getView().findViewById(R.id.employer_address);
                employer_email=(EditText)getView().findViewById(R.id.employer_email);
                employer_postal=(EditText)getView().findViewById(R.id.employer_postal);
                name =employer_name.getText().toString();
                address =employer_address.getText().toString();
                email=employer_email.getText().toString();
                mobile_no= employer_phone.getText().toString();
                postal_code=employer_postal.getText().toString();
                township = 0;
                city =0;
                country=0;
                uploadempprofilesecond(id,name,mobile_no,email,address,township,postal_code,city,country);
            }
        });
      //
//         userid();
        return rootView;

    }


        public void putdata(){

        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        employer_name=(EditText)getView().findViewById(R.id.employer_name);
        employer_address=(EditText)getView().findViewById(R.id.employer_address);
        employer_email=(EditText)getView().findViewById(R.id.employer_email);
        employer_phone=(EditText)getView().findViewById(R.id.employer_phone);
        employer_postal=(EditText)getView().findViewById(R.id.employer_postal);
        employer_name.setText(employer_Name);
        employer_address.setText(employer_Useraddress);
        employer_email.setText(employer_Useremail);
        employer_phone.setText(employer_Userphone);
        employer_postal.setText(employer_Userpostal);
    }


    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {

        if (!serverEvent.getServerResponse().equals(null)) {
            List<employerprofile>empproputs=serverEvent.getServerResponse().getEmployerprofiles();
            Toast.makeText(getContext(),"blabla",Toast.LENGTH_LONG).show();
 //           List<employer>employers=serverEvent.getServerResponse().getEmployerprofile();
         //   employer Employer=employers.get(0);
//            employer_Name=Employer.getempname().toString();
//            employer_Useraddress=Employer.getempaddress().toString();
//            employer_Useremail=Employer.getempemail().toString();
//            employer_Userphone=Employer.getempmobile().toString();
//            employer_Userpostal=Employer.getPostal_code().toString();
//            employer_name=(EditText)getView().findViewById(R.id.employer_name);
//            employer_email=(EditText)getView().findViewById(R.id.employer_email);
//            employer_address=(EditText)getView().findViewById(R.id.employer_address);
//            employer_postal=(EditText)getView().findViewById(R.id.employer_postal);
//            employer_phone=(EditText)getView().findViewById(R.id.employer_phone);
//            employer_name.setText(employer_Name);
//            employer_address.setText(employer_Useraddress);
//            employer_postal.setText(employer_Userpostal);
//            employer_email.setText(employer_Useremail);
//            employer_phone.setText(employer_Userphone);
          //  Toast.makeText(getContext(), "Success ServerEvent Respond" +employer_Name, Toast.LENGTH_LONG).show();
        }
    }

    public void userid(){
Toast.makeText(getContext(),userupload_idpost,Toast.LENGTH_LONG).show();
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
//
//    @Override
//    public void onClick(View v) {
//
//    }


    public void uploadempprofilesecond(String id,String name,String mobile_no,String email,String address,int township,String postal_code,int city,int country)
    {

        communicator.employerprofilesecond(id,token,name,mobile_no,email,address,township,postal_code,city,country);

    }
}
