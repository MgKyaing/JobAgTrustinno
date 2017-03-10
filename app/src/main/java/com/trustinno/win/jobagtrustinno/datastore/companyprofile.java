package com.trustinno.win.jobagtrustinno.datastore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zarni on 3/6/17.
 */

public class companyprofile  {

    final String company_name;
    final String address;
    final int township_id;
    final String postal_code;
    final  int city_id;
    final  int country_id;
    final  String mobile_no;
    final String email;
    final String website;
    final String description;

    public companyprofile(String company_name, String address, int township_id, String postal_code, int city_id, int country_id, String mobile_no, String email, String website, String description) {
        this.company_name = company_name;
        this.address = address;
        this.township_id = township_id;
        this.postal_code = postal_code;
        this.city_id = city_id;
        this.country_id = country_id;
        this.mobile_no = mobile_no;
        this.email = email;
        this.website = website;
        this.description = description;
    }

}
