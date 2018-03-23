package com.example.ht2s.projectconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DummyActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView UserEmail;
    private Button BtnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,login.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        UserEmail = (TextView)findViewById(R.id.hometv1);
        UserEmail.setText("Welcome "+ user.getEmail());
        BtnSignOut = (Button)findViewById(R.id.SignOutBtn);

        BtnSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == BtnSignOut)
        {
            Toast.makeText(DummyActivity.this,"Signing Out..",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(DummyActivity.this,login.class));
        }
    }

}
