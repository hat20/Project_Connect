package com.example.ht2s.projectconnect;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private EditText fname,lname,projectname,topic,desc;
    private Button update,back;
    RadioGroup radioGroup;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String gen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        update = (Button)findViewById(R.id.updateBtn);
        back = (Button)findViewById(R.id.Btnbck);
        fname = (EditText)findViewById(R.id.profirstname);
        lname = (EditText)findViewById(R.id.prolastname);
        projectname = (EditText)findViewById(R.id.projectname);
        topic = (EditText)findViewById(R.id.protopic);
        desc = (EditText)findViewById(R.id.description);
        radioGroup =(RadioGroup)findViewById(R.id.proradioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.promale:
                        gen = "Male";
                        break;
                    case R.id.profemale:
                        gen = "Female";
                        break;
                }
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        /*if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getActivity(),HomeActivity.class));
        }*/

        databaseReference = FirebaseDatabase.getInstance().getReference();

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(view == back)
                {
                    finish();
                    startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Fname = fname.getText().toString().trim();
                String Lname = lname.getText().toString().trim();
                String Proname = projectname.getText().toString().trim();
                String Protopic = topic.getText().toString().trim();
                String Prodesc = desc.getText().toString().trim();



                if(TextUtils.isEmpty(Fname)){
                    //TextUtils used to check whether data was entered by the user or not
                    Toast.makeText(ProfileActivity.this,"Please enter your first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Lname)){
                    Toast.makeText(ProfileActivity.this,"Please enter your last name", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(Proname)){
                    Toast.makeText(ProfileActivity.this,"Please enter your project's name", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(Protopic)){
                    Toast.makeText(ProfileActivity.this,"Please enter your project's topic", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(Prodesc)){
                    Toast.makeText(ProfileActivity.this,"Please give a description of your project", Toast.LENGTH_SHORT).show();
                    return;

                }

                final ProfileUpdation profileUpdation = new ProfileUpdation(Fname,Lname,gen,Proname,Protopic,Prodesc);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(profileUpdation);

                Toast.makeText(ProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();



            }
        });

    }



}
