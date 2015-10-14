package com.example.tyson.retrofitexample.Model;

import com.google.gson.annotations.SerializedName;

public class StandardResolution {

    @SerializedName(value="standard_resolution")

    public String url;
    public int width;
    public int height;
}