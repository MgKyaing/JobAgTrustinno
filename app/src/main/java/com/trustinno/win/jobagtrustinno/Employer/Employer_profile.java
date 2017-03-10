package com.trustinno.win.jobagtrustinno.Employer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.Server.ServerEventSpinnerTownship;
import com.trustinno.win.jobagtrustinno.Server.ServerEventSpinnercity;
import com.trustinno.win.jobagtrustinno.datastore.City;
import com.trustinno.win.jobagtrustinno.datastore.Township;
import com.trustinno.win.jobagtrustinno.datastore.employer;
import com.trustinno.win.jobagtrustinno.datastore.employerprofile;
import com.trustinno.win.jobagtrustinno.datastore.empproput;

import java.util.ArrayList;
import java.util.List;


public class Employer_profile extends Fragment{

    private String  user_id,employer_id,employer_Name,employer_Userphone, employer_Useremail, employer_Useraddress, employer_Userpostal;
    private EditText employer_name,employer_phone,employer_email,employer_address,employer_postal;
    String token =LoginActivity.token;
    ConnectionHub communicator;
    String name,mobile_no,email,address,postal_code;
    int township,city,country;
    int township_id,city_id,country_id;
    private List spcityid=new ArrayList<>();
    private List spcity=new ArrayList<>();
    private List sptownshipid=new ArrayList<>();
    private List sptownship=new ArrayList<>();
    private Spinner spinner1,spinner2;
    private ArrayAdapter<String> adapter1,adapter2;
    private String id;
    public Employer_profile() {
        communicator=new ConnectionHub();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {

        View rootView=inflater.inflate(R.layout.fragment_employerprofile, container, false);
        int city_id_sp=0;
        int township_id_sp=0;
        String township="";
        String city="";
        postcity(city_id_sp,city);
        posttownship(township_id_sp,township);
        spcityspinner(rootView);
        sptownshipspinner(rootView);
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
                country_id=1;
                Spcity spcity=new Spcity();
                Sptownship sptownship=new Sptownship();
                township_id=sptownship.getTownshipid();
                city_id=spcity.getCityid();
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

                uploadempprofilesecond(id,name,mobile_no,email,address,township_id,postal_code,city_id,country_id);
            }
        });
        return rootView;

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

    public void postcity(int city_id_sp,String city)
    {
        communicator.getspcity(city_id_sp,city);
    }

    public void posttownship(int township_id_sp,String township)
    {
        communicator.gettownship(township_id_sp,township);
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {

        if (!serverEvent.getServerResponse().equals(null)) {
            Toast.makeText(getContext(),"blabla",Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe
    public void onServeerEvent(ServerEventSpinnercity serverEventSpinnercity)
    {
        if (!serverEventSpinnercity.getServerResponse().equals(null))
        {
            List<City>cities=serverEventSpinnercity.getServerResponse().getresult();
            for (int i=0;i<cities.size();i++)
            {
                spcityid.add(cities.get(i).getid());
                spcity.add(cities.get(i).getcity());
            }
            spinner1=(Spinner)getActivity().findViewById(R.id.employer_city_spinner);
            adapter1=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,spcity);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter1);
        }
    }

    @Subscribe
    public void onServeerEvent(ServerEventSpinnerTownship serverEventSpinnerTownship)
    {
        if (!serverEventSpinnerTownship.getServerResponse().equals(null))
        {
            List<Township>townships=serverEventSpinnerTownship.getServerResponse().getTownships();
            for (int i=0;i<townships.size();i++)
            {
                sptownshipid.add(townships.get(i).getid());
                sptownship.add(townships.get(i).gettownship());
            }
            spinner2=(Spinner)getActivity().findViewById(R.id.employer_township_spinner);
            adapter2=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_dropdown_item,sptownship);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
        }
    }


    public void spcityspinner(View v)
    {
        spinner1=(Spinner)v.findViewById(R.id.compro_citysp);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int spcity_id=  spcityid.size();
                Spcity spcity=new Spcity();
                spcity.setCityid(spcity_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void sptownshipspinner(View view)
    {
        spinner2=(Spinner)view.findViewById(R.id.compro_townshipsp);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int sptownship_id=  sptownshipid.size();
                Sptownship sptownship=new Sptownship();
                sptownship.setTownshipid(sptownship_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static class Spcity{
        private static int cityid;
        public void setCityid(int spcity_id){
            cityid=spcity_id;
        }
        public int getCityid()
        {
            return cityid;
        }
    }


    public static class Sptownship{
        private static int townshipid;
        public void setTownshipid(int sptownship_id){
            townshipid=sptownship_id;
        }
        public int getTownshipid()
        {
            return townshipid;
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
