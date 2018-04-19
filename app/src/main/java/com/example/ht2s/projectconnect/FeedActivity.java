package com.example.ht2s.projectconnect;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView mFeedList;
    private DatabaseReference mDatabase;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);

        mFeedList = (RecyclerView)findViewById(R.id.rcv);
        mFeedList.setHasFixedSize(true);
        mFeedList.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Feed,FeedViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Feed, FeedViewHolder>(Feed.class,R.layout.row_feed,FeedViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(FeedViewHolder holder, Feed model, int position) {
                holder.setFname(model.getFname());
                holder.setPro_topic(model.getPro_topic());
                holder.setPro_name(model.getPro_name());
                holder.setPro_desc(model.getPro_desc());

            }
        };

        /*FirebaseRecyclerOptions<Feed> options =
                new FirebaseRecyclerOptions.Builder<Feed>()
                        .setQuery(mDatabase, Feed.class)
                        .build();*/

        /*FirebaseRecyclerAdapter<Feed,FeedViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Feed, FeedViewHolder>
                () {
            @Override
            protected void onBindViewHolder(@NonNull FeedViewHolder holder, int position, @NonNull Feed model) {

                holder.setFname(model.getFname());
                holder.setPro_topic(model.getPro_topic());
                holder.setPro_name(model.getPro_name());
                holder.setPro_desc(model.getPro_desc());
                linearLayout = (LinearLayout)findViewById(R.id.linearlayout1);

                holder.linearLayout.setVisibility(View.GONE);

                //if the position is equals to the item position which is to be expanded
                if (currentPosition == position) {
                    //creating an animation
                    Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

                    //toggling visibility
                    holder.linearLayout.setVisibility(View.VISIBLE);

                    //adding sliding effect
                    holder.linearLayout.startAnimation(slideDown);
                }

                holder.tvProName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //getting the position of the item to expand it
                        currentPosition = position;

                        //reloding the list
                        notifyDataSetChanged();
                    }
                });

            }

            @Override
            public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feed, parent, false);
                //return new FeedViewHolder(v);
                return null;
            }
        };*/
        mFeedList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
       // public View linearLayout;

        public FeedViewHolder(View itemView){
            super(itemView);
            mView = itemView;
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
