package com.example.ht2s.projectconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private TextView signup;
    private Button login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        login = (Button)findViewById(R.id.LoginBtn);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        signup = (TextView)findViewById(R.id.tvSignUp);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v == login){
           // loginUser();
        }

        if(v == signup){
            startActivity(new Intent(login.this,SignupActivity.class));
        }


    }
}
