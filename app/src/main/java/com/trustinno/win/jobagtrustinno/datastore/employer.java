package com.trustinno.win.jobagtrustinno.datastore;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zarni on 3/1/17.
 */

public class employer {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private  String  email;
    @SerializedName("mobile_no")
    private String mobile_no;
    @SerializedName("address")
    private String address;
    @SerializedName("township_id")
    private String township_id;
    @SerializedName("postal_code")
    private String  postal_code;
    @SerializedName("city_id")
    private String city_id;



    public employer(String id, String name, String email,String mobile_no,String address,String postal_code,String township_id,String city_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile_no=mobile_no;
        this.address=address;
        this.township_id=township_id;
        this.city_id=city_id;
        this.postal_code=postal_code;
    }


    public String getempid(){
        return id;

    }
    public String getempemail(){
        return email;
    }
    public String getPostal_code(){
        return postal_code;
    }
    public String getempaddress(){
        return address;
    }
    public String getempmobile(){
        return mobile_no;
    }
    public String getempname(){
        return name;
    }
}
