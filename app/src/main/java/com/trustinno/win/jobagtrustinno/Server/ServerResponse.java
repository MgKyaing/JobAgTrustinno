package com.trustinno.win.jobagtrustinno.Server;
import com.google.gson.annotations.SerializedName;
import com.trustinno.win.jobagtrustinno.datastore.User;
import com.trustinno.win.jobagtrustinno.datastore.employer;
import com.trustinno.win.jobagtrustinno.datastore.employerprofile;

import java.util.List;
/**
 * Created by zarni on 1/30/17.
 */
public class ServerResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("id")
    private String id;

    @SerializedName("login_name")
    private String login_name;

    @SerializedName("employer")
    private List<employer> employerList;

    @SerializedName("email")
    private String email;

    @SerializedName("telephone_no")
    private String  telephone_no;


    @SerializedName("error")
    private Boolean error;

    @SerializedName("result")
    private List<employerprofile> employerprofiles;

    @SerializedName("status_code")
    private int statusCode;

    @SerializedName("user")
     private List<User> userList;

    public  ServerResponse(String id,String login_name,String telephone_no,Boolean error){
            this.id=id;
        this.error=error;
        this.login_name=login_name;
        this.telephone_no=telephone_no;

    }
    public List<employerprofile> getEmployerprofiles(){return employerprofiles;}

    public List<User> getUserList(){
        return userList;
    }

    public String gettelephone(){
    return telephone_no;
}

    public String getToken() {
        return token;
    }

    public Boolean getError() {
        return error;
    }

    public List<employer>getEmployerprofile(){
            return employerList;
        }

    public String getId() {

        return id;
    }
    public String getLogin_name(){
        return login_name;
    }
}
