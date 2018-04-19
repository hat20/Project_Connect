package com.example.ht2s.projectconnect;

/**
 * Created by HT2S on 19-04-2018.
 */

public class ProfileUpdation {
    public String fname;
    public String lname;
    public String gen;
    public String pro_name;
    public String pro_topic;
    public String pro_desc;

    public ProfileUpdation(){

    }

    public ProfileUpdation(String fname,String lname,String gen,String pro_name,String pro_topic,String pro_desc){
        this.fname = fname;
        this.lname = lname;
        this.gen = gen;
        this.pro_name = pro_name;
        this.pro_topic = pro_topic;
        this.pro_desc = pro_desc;
    }
}
