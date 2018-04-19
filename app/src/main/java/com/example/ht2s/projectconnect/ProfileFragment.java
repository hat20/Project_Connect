package com.example.ht2s.projectconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HT2S on 19-04-2018.
 */

public class ProfileFragment extends Fragment {
    private EditText fname,lname,projectname,topic,desc;
    private Button update;
    RadioGroup radioGroup;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String gen;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        update = (Button)view.findViewById(R.id.updateBtn);
        fname = (EditText)view.findViewById(R.id.profirstname);
        lname = (EditText)view.findViewById(R.id.prolastname);
        projectname = (EditText)view.findViewById(R.id.projectname);
        topic = (EditText)view.findViewById(R.id.protopic);
        desc = (EditText)view.findViewById(R.id.description);
        radioGroup =(RadioGroup)view.findViewById(R.id.proradioGroup);

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
                    Toast.makeText(getActivity(),"Please enter your first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Lname)){
                    Toast.makeText(getActivity(),"Please enter your last name", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(Proname)){
                    Toast.makeText(getActivity(),"Please enter your project's name", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(Protopic)){
                    Toast.makeText(getActivity(),"Please enter your project's topic", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(Prodesc)){
                    Toast.makeText(getActivity(),"Please give a description of your project", Toast.LENGTH_SHORT).show();
                    return;

                }

                final ProfileUpdation profileUpdation = new ProfileUpdation(Fname,Lname,gen,Proname,Protopic,Prodesc);

                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).setValue(profileUpdation);

                Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();



            }
        });

    }

}
