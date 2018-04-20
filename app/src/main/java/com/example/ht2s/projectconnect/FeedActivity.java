package com.example.ht2s.projectconnect;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

public class FeedActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mFeedList;
    private DatabaseReference mDatabase;
    private Button homeBtn,MessBtn;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        homeBtn = (Button)findViewById(R.id.HomeAcBtn);
        MessBtn = (Button)findViewById(R.id.MessagesBtn);


        mFeedList = (RecyclerView)findViewById(R.id.rcv);
        mFeedList.setHasFixedSize(true);
        mFeedList.setLayoutManager(new LinearLayoutManager(this));

        homeBtn.setOnClickListener(this);
        MessBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v == homeBtn){
            finish();
            startActivity(new Intent(FeedActivity.this,HomeActivity.class));
        }

        if(v == MessBtn){
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setType("message/rfc822");
            i.setData(Uri.parse("mailto:"));
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
            i.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
            i.putExtra(Intent.EXTRA_TEXT   , "Body of email");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(FeedActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }


    }
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Feed,FeedViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Feed, FeedViewHolder>(Feed.class,R.layout.row_feed,FeedViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(FeedViewHolder holder, Feed model, int position) {
                holder.messBtn.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        /// button click event
                        Intent i = new Intent(Intent.ACTION_SENDTO);
                        i.setType("message/rfc822");
                        i.setData(Uri.parse("mailto:"));
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
                        i.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                        i.putExtra(Intent.EXTRA_TEXT   , "Body of email");
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(FeedActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                holder.setFname(model.getFname());
                holder.setPro_topic(model.getPro_topic());
                holder.setPro_name(model.getPro_name());
                holder.setPro_desc(model.getPro_desc());
            }
        };
        mFeedList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        Button messBtn;

        public FeedViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            this.messBtn = (Button)mView.findViewById(R.id.msgButton);
        }
        public void setFname(String fname){
            TextView tv7 = (TextView)mView.findViewById(R.id.tv7);
            tv7.setText(fname);
        }
        public void setPro_topic(String pro_topic){
            TextView tvProTopic = (TextView)mView.findViewById(R.id.tvProTopic);
            tvProTopic.setText(pro_topic);
        }
        public void setPro_name(String pro_name){
            TextView tvProName = (TextView)mView.findViewById(R.id.tvProName);
            tvProName.setText(pro_name);
        }
        public void setPro_desc(String pro_desc){
            TextView tvprodesc = (TextView)mView.findViewById(R.id.tvprodesc);
            tvprodesc.setText(pro_desc);
        }
    }
}
