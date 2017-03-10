package com.trustinno.win.jobagtrustinno.Server;

import android.util.Log;
import com.squareup.otto.Produce;
import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;
import com.trustinno.win.jobagtrustinno.Authentication.login;
import com.trustinno.win.jobagtrustinno.Authentication.register;
import com.trustinno.win.jobagtrustinno.Employer.Employer_profile;
import com.trustinno.win.jobagtrustinno.Interface.Interface;
import com.trustinno.win.jobagtrustinno.datastore.companyprofile;
import com.trustinno.win.jobagtrustinno.datastore.empproput;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.trustinno.win.jobagtrustinno.Authentication.LoginActivity.token;

/**
 * Created by zarni on 1/25/17.
 */

public class ConnectionHub {
    private static final String TAG = "ConnectionHub";
    private static final String SERVER_URL = "http://goldenictsolutions.com/";
//    private static final String token= LoginActivity.token;

    public void loginPost(String login_name, String password) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();


        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.post(new login(login_name, password));
        call.enqueue(new Callback<ServerResponse>() {

            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                Log.e(TAG, "Success" + response.message());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));

            }
        });

    }

    public void registerPost(String login_name, String email,String telephone_no, String password, String user_type,int category_id) {

        final HttpLoggingInterceptor register = new HttpLoggingInterceptor();
        register.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(register);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.register(new register(login_name, email,telephone_no, password, user_type,category_id));
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success" + response.message());
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void employerprofile(String user_id,String token ) {

        final HttpLoggingInterceptor register = new HttpLoggingInterceptor();
        register.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(register);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.employerprofile(user_id,token);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success" + response.message());
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }

    public void employerprofilesecond(String id,String token,String name, String mobile_no, String email, String address, int township, String postal_code, int city, int country) {

        final HttpLoggingInterceptor register = new HttpLoggingInterceptor();
        register.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(register);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.employerprofilesecond(id,token,new empproput(name,mobile_no,email,address,township,postal_code,city,country));
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success" + response.message());
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void companyprofile(String company_name, String address, int township_id, String postal_code, int city_id, int country_id, String mobile_no, String email, String website, String description){
        final HttpLoggingInterceptor register = new HttpLoggingInterceptor();
        register.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(register);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL).build();

        Interface service = retrofit.create(Interface.class);

        Call<ServerResponse> call = service.companyprofile(token,new companyprofile(company_name,address,township_id,postal_code,city_id,country_id,mobile_no,email,website,description));
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                // response.isSuccessful() is true if the response code is 2xx
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                BusProvider.getInstance().post(new ServerEvent(response.body()));
                Log.e(TAG, "Success" + response.message());
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure " + t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
            }
        });
    }


    public void gettownship(int township_id_sp,String township){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        Interface service = retrofit.create(Interface.class);
        //Call<ServerResponse> call = service.post("login",username,password);
        //Call<ServerResponse> call = service.post(username,password);
        Call<ServerResponse> call = service.gettown(township_id_sp,township);
        //Call<ServerResponse> call = service.post(username,password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                Log.e(TAG, "Success" + response.message());
                BusProvider.getInstance().post(new ServerEventSpinnerTownship(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure "+t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));

            }

        });

    }


    public void getspjobcate(int cate_id_sp,String category){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        Interface service = retrofit.create(Interface.class);
        //Call<ServerResponse> call = service.post("login",username,password);
        //Call<ServerResponse> call = service.post(username,password);
        Call<ServerResponse> call = service.getcate(cate_id_sp,category);
        //Call<ServerResponse> call = service.post(username,password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                Log.e(TAG, "Success" + response.message());
                BusProvider.getInstance().post(new ServerEventSpinnerCate(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure "+t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));

            }

        });

    }
    public void getspjobtype(int jobtype_id_sp,String jobtype){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        Interface service = retrofit.create(Interface.class);
        //Call<ServerResponse> call = service.post("login",username,password);
        //Call<ServerResponse> call = service.post(username,password);
        Call<ServerResponse> call = service.gettype(jobtype_id_sp,jobtype);
        //Call<ServerResponse> call = service.post(username,password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                Log.e(TAG, "Success" + response.message());
                BusProvider.getInstance().post(new ServerEventSpinnerCate(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure "+t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));

            }

        });

    }

    public void getspcity(int city_id_sp,String city){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();
        Interface service = retrofit.create(Interface.class);
        //Call<ServerResponse> call = service.post("login",username,password);
        //Call<ServerResponse> call = service.post(username,password);
        Call<ServerResponse> call = service.getcity(city_id_sp,city);
        //Call<ServerResponse> call = service.post(username,password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e(TAG, "Success" + response.code());
                Log.e(TAG, "Success" + response.body());
                Log.e(TAG, "Success" + response.message());
                BusProvider.getInstance().post(new ServerEventSpinnercity(response.body()));
                Log.e(TAG, "Success");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // handle execution failures like no internet connectivity
                Log.e(TAG, "Failure "+t.getMessage());
                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));

            }

        });

    }






//TODO to fixed login server response app crash error
//
//    public void getemployerjob(String name, String company_name, String moblie_no, String contact_no, String user_email, String normal_email, String address, String township, String postal_code, int city, int country, String website, String description) {
//
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(logging);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .client(httpClient.build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(SERVER_URL)
//                .build();
//
//        Interface service = retrofit.create(Interface.class);
//        Call<ServerResponse> call = service.employerjob_upload(new employerjob_upload(name,company_name,moblie_no,contact_no,user_email,normal_email,address,township,postal_code,city,country,website,description));
//
//        call.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                // response.isSuccessful() is true if the response code is 2xx
//                Log.e(TAG, "Success" + response.code());
//                Log.e(TAG, "Success" + response.body());
//                BusProvider.getInstance().post(new ServerEvent(response.body()));
//                Log.e(TAG, "Success" + response.message());
//                Log.e(TAG, "Success");
//            }
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                BusProvider.getInstance().post(new ErrorEvent(-2, t.getMessage()));
//            }
//        });
//    }

    @Produce
    public ServerEvent produceServerEvent(ServerResponse serverResponse) {
        return new ServerEvent(serverResponse);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }

}
