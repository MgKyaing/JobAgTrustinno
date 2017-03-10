package com.trustinno.win.jobagtrustinno.Employer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;

public class Employer_jobupload extends Fragment {



    ConnectionHub communicator;
    public Employer_jobupload() {

        communicator=new ConnectionHub();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {

        View rootView= inflater.inflate(R.layout.fragment_employer_jobupload, container, false);

        return rootView;

    }

    public void postjobcate(int cate_id_sp,String category)
    {
        communicator.getspjobcate(cate_id_sp,category);
    }

    public void posjobtype(int jobtype_id_sp,String jobtype)
    {
communicator.getspjobtype(jobtype_id_sp,jobtype);
    }




}