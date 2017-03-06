package com.trustinno.win.jobagtrustinno.Interface;

import android.support.annotation.Nullable;

import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.Authentication.login;
import com.trustinno.win.jobagtrustinno.Authentication.register;
import com.trustinno.win.jobagtrustinno.Employer.Employer;
import com.trustinno.win.jobagtrustinno.Employer.Employer_profile;
import com.trustinno.win.jobagtrustinno.Server.ServerResponse;
import com.trustinno.win.jobagtrustinno.datastore.empproput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
/**
 * Created by Dori on 12/28/2016.
 */
public interface Interface {

    String token=LoginActivity.token;
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            //  "Authorization: Basic ZWlwaHl1cGh5dWhsYWluZ0BnbWFpbC5jb206MTIzNDU2Nzg="
    })
   // @FormUrlEncoded
    @POST("/api/login")
        //Call<ServerResponse> post(@Body @Root("email") String email,@Root("password") String password);
    Call<ServerResponse> post(@Body login body);
    //Call<ServerResponse> post(@Body login login);
   /* Call<ServerResponse> post(
            //@Field("method") String method,
            @Field("email") String username,
            @Field("password") String password
    );*/

    @GET("/api/employer/{user_id}")
    Call<ServerResponse>employerprofile(@Path("user_id")String user_id,@Query("token")String token

    );


    @PUT("/api/employer/{id}")
    Call<ServerResponse>employerprofilesecond(@Path( "id") String id ,@Query("token")String token,@Body empproput empproput);

    @GET("/api/login")
    Call<ServerResponse> get(
            // @Query("method") String method,
            @Query("email") String username,
            @Query("password") String password
    );

    @POST("/api/register")
    Call<ServerResponse> register(
            @Body register register
    );



    //   @GET("/api/register")
    // Call<ServerResponseRegister> pull(
    // @Query("method") String method,
    //     @Query("login_name") String login_name,
    //     @Query("email") String email,
    //    @Query("password") String password,
    /////    @Query("password_confirmation") String password_confirmation
    // );
}