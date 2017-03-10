package com.trustinno.win.jobagtrustinno.Server;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zarni on 3/10/17.
 */
public class Jobtype implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    public Jobtype(int id, String type) {
        this.id = id;
        this.type = type;
    }
    public int getjobtypeid(){
        return id;
    }

    public String getjotype(){
        return type;
    }
}
