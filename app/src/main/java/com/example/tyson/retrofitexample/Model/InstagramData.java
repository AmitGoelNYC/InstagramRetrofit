package com.example.tyson.retrofitexample.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InstagramData {
    @SerializedName(value="data")
    public List<instagramclass> instagrams;

    public void setEmployees(List<instagramclass> instagrams) {
        this.instagrams = instagrams;

    }

    public static class instagramclass {
        public Object attribution;
        public List<String> tags = new ArrayList<String>();
        public Object location;
        //public Comments comments;
        public String filter;
        public String createdTime;
        public String link;
        //public Likes likes;
        public Images images;
        public List<Object> usersInPhoto = new ArrayList<Object>();
        //public Caption caption;
        public String type;
        public String id;
        //public User user;


        @Override
        public String toString() {
            return ("link is " + link);

        }
    }

}