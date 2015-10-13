package com.example.tyson.retrofitexample;

import com.example.tyson.retrofitexample.Model.InstagramData;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class RetrofitExample {

    private static InstagramApiInterface sInstagramService;

    public static InstagramApiInterface getGitHubApiClient() {
        if (sInstagramService == null) {
            //GsonBuilder gson = new GsonBuilder();
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.instagram.com")
                    //.setConverter(new GsonConverter(gson.create()))
                    .build();

            sInstagramService = restAdapter.create(InstagramApiInterface.class);
        }

        return sInstagramService;
    }

    public interface InstagramApiInterface {
        @GET("/v1/tags/{feed}/media/recent")
        /*Repo getCurators(
                @Query("api_key") String key
        );*/
        void getInstaData(@Path("feed") String feed, @Query("client_id") String clientId, Callback<InstagramData> callback);
    }
}
