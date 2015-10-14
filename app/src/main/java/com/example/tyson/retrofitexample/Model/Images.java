package com.example.tyson.retrofitexample.Model;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName(value="low_resolution")
    public LowResolution lowResolution;

    public Thumbnail thumbnail;

    @SerializedName(value="standard_resolution")
    public StandardResolution standardResolution;
}