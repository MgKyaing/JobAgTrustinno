package com.trustinno.win.jobagtrustinno.Authentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;

/**
 * A login screen that offers login via email/password.
 */

public class RegisterActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView, Telephone_no, Category_Id, mConfirmpassword;
    private EditText Login_name;
    private View mProgressView;
    private View mLoginFormView;
    private RadioGroup usertyperadio;
    private RadioButton emp_reg_radio1, emp_reg_radio2;
    private String telephone_no, category_id, login_name, email, password, User_type, user_type;
    private Button register_button;
    ConnectionHub communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        communicator = new ConnectionHub();

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


                    int checkedRadioButtonId = usertyperadio.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == emp_reg_radio1.getId()) {
                        user_type = "1";
                    } else {
                        user_type = "2";
                    }
                    login_name = Login_name.getText().toString();
                    email = mEmailView.getText().toString();
                    telephone_no = Telephone_no.getText().toString();
                    password=mConfirmpassword.getText().toString();
                    category_id = "1";
                    useRegister(login_name, email, telephone_no, password, user_type, category_id);
                    Toast.makeText(getApplicationContext(), user_type+login_name+category_id+password+email, Toast.LENGTH_LONG).show();


                }

        }

    private void useRegister(String login_name, String email, String telephone_no, String password, String user_type, String category_id) {
        communicator.registerPost(login_name, email, telephone_no, password, user_type, category_id);
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {

        if (!serverEvent.getServerResponse().equals(null)) {

            Toast.makeText(getApplicationContext(), "Success ServerEvent Respond" + serverEvent.getServerResponse(), Toast.LENGTH_LONG).show();
        }
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

    /*        public void Chooseusertype() {

            int checkedRadioButtonId=usertyperadio.getCheckedRadioButtonId();
            if (checkedRadioButtonId== -1)
            {
                Toast.makeText(getApplicationContext(),0, Toast.LENGTH_LONG).show();
            }
            else if (checkedRadioButtonId == R.id.emp_reg_radio1) {
                User_type="1";

            }
            else if (checkedRadioButtonId ==R.id.emp_reg_radio2)
            {
                User_type="2";
            }
                user_type=User_type;
           // Toast.makeText(getApplicationContext(),"this is"+rdo_type,Toast.LENGTH_LONG).show();
        }*/
    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


}
