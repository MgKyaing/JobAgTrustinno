package com.trustinno.win.jobagtrustinno.datastore;

/**
 * Created by zarni on 3/5/17.
 */

public class empproput {
    final String name;
    final String mobile_no;
    final String email;
    final String address;
    final int township;
    final String postal_code;
    final int city;
    final int country;
    public empproput(String name, String mobile_no, String email, String address, int township, String postal_code, int city, int country)
    {
        this.name=name;
        this.mobile_no=mobile_no;
        this.email=email;
        this.address=address;
        this.township=township;
        this.postal_code=postal_code;
        this.city=city;
        this.country=country;

    }

}
