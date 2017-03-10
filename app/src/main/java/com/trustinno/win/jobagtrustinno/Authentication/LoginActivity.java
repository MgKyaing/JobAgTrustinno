package com.trustinno.win.jobagtrustinno.Authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.trustinno.win.jobagtrustinno.Employer.Employer;
import com.trustinno.win.jobagtrustinno.R;
import com.trustinno.win.jobagtrustinno.Server.BusProvider;
import com.trustinno.win.jobagtrustinno.Server.ConnectionHub;
import com.trustinno.win.jobagtrustinno.Server.ErrorEvent;
import com.trustinno.win.jobagtrustinno.Server.ServerEvent;
import com.trustinno.win.jobagtrustinno.datastore.User;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private EditText mPasswordView, mEmailView;
    private View mProgressView;
    private View mLoginFormView;
    private Button email_sign_in_button;
    private Button employer_sign_in_button;
    private TextView linkregister;
    public ConnectionHub communicator;
    private String login_name, passwords, result, id, email;
    private TextView extraInformation;
    public static String token;
    public int rdoType, rdotype;
    public String userId, userlogin_name, users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        ImageView myImageView = (ImageView) findViewById(R.id.loginlogo);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(LoginActivity.this, R.animator.fade_in);
        myImageView.startAnimation(myFadeInAnimation);


        communicator = new ConnectionHub();
        linkregister = (TextView) findViewById(R.id.linkregister);
        linkregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.login_email);


        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        extraInformation = (TextView) findViewById(R.id.information);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        //      mLoginFormView = findViewById(R.id.login_form);
//        mProgressView = findViewById(R.id.login_progress);
    }


    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        //else if (!isEmailValid(email)) {
        // mEmailView.setError(getString(R.string.error_invalid_email));
        // focusView = mEmailView;
        //  cancel = true;
        //   }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        else {


            mEmailView = (EditText) findViewById(R.id.login_email);
            mPasswordView = (EditText) findViewById(R.id.login_password);
            email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
            email_sign_in_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login_name = mEmailView.getText().toString();
                    passwords = mPasswordView.getText().toString();
                    usePost(login_name, passwords);
                }
            });
        }
    }

    private void usePost(String login_name, String password) {
        communicator.loginPost(login_name, password);
    }


    @Subscribe
    public void onServerEvent(ServerEvent serverEvent) {

        if (serverEvent.getServerResponse() == null)
        {
            Toast.makeText(getApplicationContext(), "blabla", Toast.LENGTH_LONG).show();
        }

       if (serverEvent.getServerResponse() != null){
            List<User> user = serverEvent.getServerResponse().getUserList();
            User users = user.get(0);
            userId = users.getId();
            userlogin_name=users.getuser_name();
            token=serverEvent.getServerResponse().getToken();
           rdoType=users.getuser_type();

           if (rdoType ==1){
            Intent intent=new Intent(LoginActivity.this,Employer.class);
            intent.putExtra("employerid",userId);
            intent.putExtra("employername",userlogin_name);
               intent.putExtra("apptoken",token);
                startActivity(intent);

            }

           else if (rdoType == 2)
            {
                Toast.makeText(getApplicationContext(),"Hey your must be 1 not"+rdoType,Toast.LENGTH_LONG).show();

            }

            }

          //  Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_LONG).show();
            //  extraInformation.setText("" + serverEvent.getServerResponse().getToken()+serverEvent.getServerResponse());
            //     token = serverEvent.getServerResponse().getToken();

            //    result=serverEvent.getServerResponse().getLogin_name();
            //   id=serverEvent.getServerResponse().getId();
            // email=serverEvent.getServerResponse().getemail();
            //rdoType=serverEvent.getServerResponse().getrdoType();
            //Id=serverEvent.getServerResponse().getId();
            //  if (rdoType == 1)
            //{
            //   Intent intent = new Intent(this, Employer.class);
            // intent.putExtra("token", token);
            //intent.putExtra("email",email);
            //intent.putExtra("id",id);
            // startActivity(intent);

            //}
            // else if (rdoType == 2)
            //{
            //  Intent intent = new Intent(this, Employer_profile.class);
            //intent.putExtra("token", token);
            //startActivity(intent);
            //}
            // else
            // {
            //   return;

            //}

            // Intent intent = new Intent(MainLoginActivity.this, JFirstMenuPage.class);
            //    startActivity(intent)
        }
  //      if (serverEvent.getServerResponse() == null) {
    //        Toast.makeText(getApplicationContext(), "Login failed please try again", Toast.LENGTH_LONG).show();



        // if (serverEvent.getServerResponse().getToken() != null) {
        // information.setText("Username: " + serverEvent.getServerResponse().getToken() + " || Password: " + serverEvent.getServerResponse().getPassword());
        //     }

//    }


    @Subscribe
    public void onErrorEvent (ErrorEvent errorEvent){
        Toast.makeText(this, "onErrorEvent fail " + errorEvent.getErrroMsg(), Toast.LENGTH_SHORT).show();
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
    // private boolean isEmailValid(String email) {
    //TODO: Replace this with your own logic
    // return email.contains("@");
    //}

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

