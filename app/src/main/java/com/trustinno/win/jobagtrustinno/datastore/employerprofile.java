package com.trustinno.win.jobagtrustinno.datastore;

/**
 * Created by zarni on 3/6/17.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class employerprofile implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("mobile_no")
    private String mobile_no;
    @SerializedName("email")
    private  String  email;

    public employerprofile(String name, String mobile_no,String email) {
       this.name=name;
        this.email = email;
        this.mobile_no = mobile_no;
    }

}
