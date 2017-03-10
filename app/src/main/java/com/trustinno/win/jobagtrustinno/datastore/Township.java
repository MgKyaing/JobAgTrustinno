package com.trustinno.win.jobagtrustinno.datastore;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zarni on 3/9/17.
 */

public class Township  {
    @SerializedName("id")
    private int id;
    @SerializedName("township")
    private String township;
    @SerializedName("city_id")
    private String city_id;


    public Township(int  id, String township,String city_id) {
        this.id = id;
        this.township = township;
        this.city_id=city_id;
    }
    public String gettownship(){
        return township;
    }
    public int getid(){
        return id;
    }


}
