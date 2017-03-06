package com.trustinno.win.jobagtrustinno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.trustinno.win.jobagtrustinno.Authentication.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private  Button gotologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotologin=(Button)findViewById(R.id.button_to_next);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
