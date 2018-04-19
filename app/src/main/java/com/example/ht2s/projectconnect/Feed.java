package com.example.ht2s.projectconnect;

/**
 * Created by HT2S on 19-04-2018.
 */

public class Feed {
    private String fname,pro_topic,pro_name,pro_desc;

    public Feed(){

    }

    public Feed(String fname, String pro_topic, String pro_name, String pro_desc) {
        this.fname = fname;
        this.pro_topic = pro_topic;
        this.pro_name = pro_name;
        this.pro_desc = pro_desc;
    }

    public String getFname() {

        return fname;
    }

    public String getPro_topic() {
        return pro_topic;
    }

    public String getPro_name() {
        return pro_name;
    }

    public String getPro_desc() {
        return pro_desc;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setPro_topic(String pro_topic) {
        this.pro_topic = pro_topic;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public void setPro_desc(String pro_desc) {
        this.pro_desc = pro_desc;
    }

}
