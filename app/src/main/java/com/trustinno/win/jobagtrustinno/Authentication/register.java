package com.trustinno.win.jobagtrustinno.Authentication;

/**
 * Created by zarni on 1/25/17.
 */

public class register {

     final  String login_name;
    final String email;
    final String telephone_no;
    final String password;
    final  String  user_type;
    final int category_id;

    public register(String login_name, String email, String telephone_no,String password, String user_type,int category_id){

        this.login_name=login_name;
        this.email=email;
        this.telephone_no=telephone_no;
        this.password=password;
        this.user_type=user_type;
        this.category_id=category_id;

    }

}
