package com.trustinno.win.jobagtrustinno.datastore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by zarni on 3/9/17.
 */

public class SpJobCategory implements Serializable{
    @SerializedName("id")
    public int cate_id;
    @SerializedName("category")
    public String category;


    public SpJobCategory(int cate_id, String category) {
        this.cate_id = cate_id;
        this.category = category;
    }
    public int getspjobcate_id(){
        return cate_id;
    }
    public String getspjobcate_cate(){
        return category;
    }
}
