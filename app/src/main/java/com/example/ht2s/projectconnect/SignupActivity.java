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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText email;
    private EditText fname;
    private EditText lname;
    private EditText password;
    private TextView signin;
    private Button signup;
    RadioGroup radioGroup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    String gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //home activity here
            finish();
            startActivity(new Intent(SignupActivity.this,HomeActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);
        signup = (Button)findViewById(R.id.btn1);
        fname = (EditText)findViewById(R.id.firstname);
        lname = (EditText)findViewById(R.id.lastname);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        signin = (TextView)findViewById(R.id.tvSignIn);
        radioGroup =(RadioGroup)findViewById(R.id.radioGroup);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void sendEmailVerification(){
        FirebaseUser user2 = firebaseAuth.getInstance().getCurrentUser();
        if(user2 != null)
        {
            user2.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(SignupActivity.this, "Check your email for verification", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            });
        }

    }

    private void signupUser(){
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
        String Fname = fname.getText().toString().trim();
        String Lname = lname.getText().toString().trim();

        final UserInformation userInformation = new UserInformation(Fname,Lname,gen);


        progressDialog.setMessage("SIGNING UP..");
        progressDialog.show();
        Task<AuthResult> authResultTask = firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                            sendEmailVerification();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            databaseReference.child(user.getUid()).setValue(userInformation);
                            finish();
                            startActivity(new Intent(SignupActivity.this, DummyActivity.class));
                        } else {
                            Toast.makeText(SignupActivity.this, "Sign Up Failed ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onClick(View v) {

        if(v == signup){
            signupUser();
        }

        if(v == signin){
            finish();
            startActivity(new Intent(SignupActivity.this,login.class));
        }


    }

    public void checkButton(View view)
    {
        boolean checked =((RadioButton)view).isChecked();
        switch(view.getId()){
            case R.id.male:
                if(checked)
                    gen = "Male";
                break;
            case R.id.female:
                if(checked)
                    gen = "Female";
                break;
        }
    }
}
