package com.trustinno.win.jobagtrustinno.Employer;

import android.content.Context;
import android.net.Uri;
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
import com.trustinno.win.jobagtrustinno.Authentication.RegisterActivity;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.Server.ServerEventSpinnerTownship;
import com.trustinno.win.jobagtrustinno.Server.ServerEventSpinnercity;
import com.trustinno.win.jobagtrustinno.datastore.City;
import com.trustinno.win.jobagtrustinno.datastore.Township;
import com.trustinno.win.jobagtrustinno.datastore.employerprofile;

import java.util.ArrayList;
import java.util.List;

public class Employer_company_profile extends Fragment {

    ConnectionHub communicator;
    String company_name,company_logo,address,postal_code,mobile_no,email,website,description;
    EditText Company_Name,Company_Logo,Address,Postal_Code,Mobile_no,Email,Website,Description;
    int township_id,city_id,country_id;
    public List spcityid=new ArrayList<>();
    public List spcity=new ArrayList<>();
    private List sptownshipid=new ArrayList<>();
    private List sptownship=new ArrayList<>();
    Spinner spinner1,spinner2;
    ArrayAdapter<String> adapter1,adapter2;
    public Employer_company_profile() {
        communicator =new ConnectionHub();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {

        View rootView=inflater.inflate(R.layout.fragment_employer_company_profile, container, false);
         int city_id_sp=0;
        int township_id_sp=0;
        String township="";
        String city="";
        postcity(city_id_sp,city);
        posttownship(township_id_sp,township);
        spcityspinner(rootView);
        sptownshipspinner(rootView);
        Button btnempsubmit=(Button)rootView.findViewById(R.id.employer_compup);
        btnempsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                Spcity spcity=new Spcity();
                Sptownship sptownship=new Sptownship();
                township_id=sptownship.getTownshipid();
                city_id=spcity.getCityid();
                Company_Name=(EditText)getView().findViewById(R.id.employer_companyname);
                Address=(EditText)getView().findViewById(R.id.employer_companyaddress);
                Postal_Code=(EditText)getView().findViewById(R.id.employer_company_postalcode);
                Mobile_no=(EditText)getView().findViewById(R.id.employer_company_mobile);
                Email=(EditText)getView().findViewById(R.id.employer_company_email);
                Website=(EditText)getView().findViewById(R.id.employer_company_website);
                Description=(EditText)getView().findViewById(R.id.employer_company_description);
                company_name=Company_Name.getText().toString();
                address=Address.getText().toString();
                postal_code=Postal_Code.getText().toString();
                mobile_no=Mobile_no.getText().toString();
                email=Email.getText().toString();
                website=Website.getText().toString();
                description=Description.getText().toString();
                post(company_name,address,township_id,postal_code,city_id,country_id,mobile_no,email,website,description);
            }
        });

        return rootView;
    }


    public void post(String company_name, String address, int township_id, String postal_code, int city_id, int country_id, String mobile_no, String email, String website, String description)
    {
            communicator.companyprofile(company_name,address,township_id,postal_code,city_id,country_id,mobile_no,email,website,description);
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
             spinner1=(Spinner)getActivity().findViewById(R.id.compro_citysp);
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
             spinner2=(Spinner)getActivity().findViewById(R.id.compro_townshipsp);
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

}
