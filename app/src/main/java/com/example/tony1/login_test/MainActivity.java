package com.example.tony1.login_test;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private int counter;
    private TextView UserSignUp;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.firstName);
        Password = (EditText)findViewById(R.id.password);
        Login = (Button)findViewById(R.id.loginButton);
        info = (TextView)findViewById(R.id.info);
        UserSignUp = (TextView) findViewById(R.id.tvUserSignUp);

        counter = 5;

        info.setText("No of attempts remain: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        UserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });

    }

    private void validate (String UserName, String UserPassword) {
        if ((UserName.equals("admin")) && (UserPassword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, Logged_in.class);
            startActivity(intent);
        } else {
            counter--;

            info.setText("No of attempts remain: " + String.valueOf(counter));

            if (counter == 0) {
                Login.setEnabled(false);
            }
        }

    }
}
