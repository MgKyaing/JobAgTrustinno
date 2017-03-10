package com.trustinno.win.jobagtrustinno.datastore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zarni on 3/9/17.
 */

public class City implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("city")
    private String city;
    @SerializedName("country_id")
    private String country_id;


    public City(int  id, String city,String country_id) {
        this.id = id;
        this.city = city;
        this.country_id=country_id;
    }
    public String getcity(){
        return city;
    }
    public int getid(){
        return id;
    }

}
