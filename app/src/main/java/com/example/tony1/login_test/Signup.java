package com.example.tony1.login_test;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail;
    private Button regButton;
    private TextView UserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_signup);
        setupUIViews();

        UserName = (EditText)findViewById(R.id.etUserName);
        UserPassword = (EditText)findViewById(R.id.etUserPassword);
        UserEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnSignUp);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) ;
                {
                    //upload data too database
                }
            }
        });

        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupUIViews() {
        UserName = (EditText)findViewById(R.id.etUserName);
        UserPassword = (EditText)findViewById(R.id.etUserPassword);
        UserEmail = (EditText)findViewById(R.id.etUserEmail);
        UserLogin = (TextView) findViewById(R.id.tvUserLogin);
        regButton = (Button) findViewById(R.id.btnSignUp);

    }

    private boolean validate() {
        boolean result = false;

        String name = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();

        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "please enter al the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;

    }

}
