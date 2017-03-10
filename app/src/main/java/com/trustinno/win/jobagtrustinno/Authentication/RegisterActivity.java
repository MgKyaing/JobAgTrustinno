package com.trustinno.win.jobagtrustinno.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.Server.ServerEventSpinnerCate;
import com.trustinno.win.jobagtrustinno.Server.ServerEventSpinnercity;
import com.trustinno.win.jobagtrustinno.datastore.SpJobCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView, Telephone_no, Category_Id, mConfirmpassword;
    private EditText Login_name;
    private RadioGroup usertyperadio;
    private RadioButton emp_reg_radio1;
    private String telephone_no, login_name, email, password,  user_type;
    private Button register_button;
    ConnectionHub communicator;
    public List spcategory = new ArrayList<>();
    private List spcategoryid=new ArrayList<>();
    private Spinner spinner;
    int category_id;
    int categoryspid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        communicator = new ConnectionHub();
        int cate_id = 0;
        String category = "";
        usertyperadio = (RadioGroup) findViewById(R.id.emp_radio_group);
        emp_reg_radio1 = (RadioButton) findViewById(R.id.emp_reg_radio1);
        mPasswordView = (EditText) findViewById(R.id.password);
        usertyperadio = (RadioGroup) findViewById(R.id.emp_radio_group);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_register);
        Login_name = (EditText) findViewById(R.id.register_name);
        Telephone_no = (EditText) findViewById(R.id.register_phoneno);
        mConfirmpassword = (EditText) findViewById(R.id.confirmpassword);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_register);
        mPasswordView = (EditText) findViewById(R.id.password);
        postspcate(cate_id, category);
        load_spinner();
        register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempregister();

            }
        });

        //           login_name = Login_name.getText().toString();
        //         email = mEmailView.getText().toString();
        //       password = mPasswordView.getText().toString();
        //       useRegister(login_name, email, password, user_type);
        //     Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        //   startActivity(intent);
        //  mLoginFormView = findViewById(R.id.login_form);
        //    mProgressView = findViewById(R.id.login_progress);
    }


    public void attempregister() {

        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        final String Email = mEmailView.getText().toString();
        final String Password = mPasswordView.getText().toString();
        final String Confrimpassword = mConfirmpassword.getText().toString();
        final String Loginname = Login_name.getText().toString();
        final String Telephoneno = Telephone_no.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(Password) && !isPasswordValid(Password)) {
            mPasswordView.setError("Password length is too short.You must type at least 5");
            focusView = mPasswordView;
            cancel = true;

        }

        if (!Password.equals(Confrimpassword)) {
            mConfirmpassword.setError("Password didn't match ");
            focusView = mConfirmpassword;
            cancel = true;

        }

        if (TextUtils.isEmpty(Loginname) && TextUtils.isEmpty(Telephoneno)) {
            Login_name.setError(getString(R.string.error_field_required));
            focusView = Login_name;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            Parsespid parsespid=new Parsespid();

            int checkedRadioButtonId = usertyperadio.getCheckedRadioButtonId();
            if (checkedRadioButtonId == emp_reg_radio1.getId()) {
                user_type = "2";
            } else {
                user_type = "1";
            }
            login_name = Login_name.getText().toString();
            email = mEmailView.getText().toString();
            telephone_no = Telephone_no.getText().toString();
            password = mConfirmpassword.getText().toString();
            category_id=parsespid.getCateid();
            categoryspid=category_id;
            useRegister(login_name, email, telephone_no, password, user_type, categoryspid);
            Toast.makeText(getApplicationContext(),  categoryspid+user_type , Toast.LENGTH_LONG).show();


        }

    }

    public void postspcate(int cate_id, String category) {
        communicator.getspjobcate(cate_id, category);
    }

    private void useRegister(String login_name, String email, String telephone_no, String password, String user_type, int category_id) {
        communicator.registerPost(login_name, email, telephone_no, password, user_type, category_id);
    }


    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {

        if (!serverEvent.getServerResponse().equals(null)) {


            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    @Subscribe
    public void onServerEvent(ServerEventSpinnerCate serverEventSpinnerCate) {
        if (!serverEventSpinnerCate.getServerResponse().equals(null)) {

            List<SpJobCategory>jobCategoryList=serverEventSpinnerCate.getServerResponse().getspjobcategories();
            for (int i=0;i<jobCategoryList.size();i++)
            {
                spcategory.add(jobCategoryList.get(i).getspjobcate_cate());
                spcategoryid.add(jobCategoryList.get(i).getspjobcate_id());
            }
//            Toast.makeText(getApplicationContext(), (CharSequence) spcategory,Toast.LENGTH_LONG).show();
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(RegisterActivity.this,android.R.layout.simple_spinner_dropdown_item,spcategory);
          Spinner  spinner=(Spinner)findViewById(R.id.register_spinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

        } else if (serverEventSpinnerCate.getServerResponse().equals(null)) {
            Toast.makeText(getApplicationContext(), "Failed to load spinner please check your internet connection", Toast.LENGTH_LONG).show();
        }
    }

    public void load_spinner(){
        spinner=(Spinner)findViewById(R.id.register_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int jobcate_id=  spcategoryid.size();

                    Parsespid parsespid=new Parsespid();
                parsespid.setCateid(jobcate_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static class Parsespid{
        private static int cateid;
            public void setCateid(int jobcate_id){
                    cateid=jobcate_id;
            }
            public int getCateid(){return cateid;}

    }



    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


}
