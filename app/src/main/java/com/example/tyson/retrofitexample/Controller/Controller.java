package com.example.tyson.retrofitexample.Controller;

import android.text.TextUtils;
import android.util.Log;

import com.example.tyson.retrofitexample.Model.InstagramData;
import com.example.tyson.retrofitexample.Model.RetrofitExample;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ag on 10/12/2015.
 */
public class Controller {

    private static final String CLIENT_ID = "5f9365e9f1054aa991726d731c65aa02";
    private static final String TAG = Controller.class.getSimpleName();
    private InstagramCallbackListener mListener;

    public Controller(InstagramCallbackListener listener) {
        mListener = listener;
    }

    public void startFetching(final String searchText) {

        if (!TextUtils.isEmpty(searchText)) {
            RetrofitExample.getGitHubApiClient().getInstaData(searchText, CLIENT_ID, new Callback<InstagramData>() {
                @Override
                public void success(InstagramData instaData, Response response) {
                    Log.d(TAG, response.getUrl());
                    for (int i = 0; i < instaData.instagrams.size(); i++) {
                        Log.d(TAG, searchText + " Username - " + instaData.instagrams.get(i).user.username);
                    }

                    mListener.onFetchProgress(instaData.instagrams);
                    mListener.onFetchComplete();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.d(TAG, "Retrofit failed - " + retrofitError.getMessage());
                }
            });
        }
    }

    public interface InstagramCallbackListener {
        void onFetchProgress(List<InstagramData.instagramclass> instagramclass);
        void onFetchComplete();
        void onFetchFailed();
    }
}
