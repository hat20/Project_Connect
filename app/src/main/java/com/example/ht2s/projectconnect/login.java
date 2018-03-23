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
import com.google.firebase.auth.FirebaseUser;

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

        if(firebaseAuth.getCurrentUser() != null){
            //home activity here
            finish();
            startActivity(new Intent(login.this,HomeActivity.class));
        }

        progressDialog = new ProgressDialog(this);
        login = (Button)findViewById(R.id.LoginBtn);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        signup = (TextView)findViewById(R.id.tvSignUp);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

    }

    private void loginUser()
    {
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            //TextUtils used to check whether Email was entered by user or not
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;

        }
        progressDialog.setMessage("SIGNING IN..");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            checkIfEmailVerified();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void checkIfEmailVerified(){
        FirebaseUser user3 = firebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = user3.isEmailVerified();
        if(emailVerified)
        {
            startActivity(new Intent(login.this,HomeActivity.class));
        }
        else{
            startActivity(new Intent(login.this,DummyActivity.class));
        }
    }

    @Override
    public void onClick(View v) {

        if(v == login){
           loginUser();
        }

        if(v == signup){
            finish();
            startActivity(new Intent(login.this,SignupActivity.class));
        }


    }
}
