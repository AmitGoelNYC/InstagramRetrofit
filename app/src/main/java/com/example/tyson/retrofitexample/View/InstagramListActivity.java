package com.example.tyson.retrofitexample.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.tyson.retrofitexample.Model.InstagramAdapter;
import com.example.tyson.retrofitexample.Model.InstagramData;
import com.example.tyson.retrofitexample.R;
import com.example.tyson.retrofitexample.RetrofitExample;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InstagramListActivity extends AppCompatActivity {

    //private static final String USER = "AMITGOELNYC";
    private static final String CLIENT_ID = "5f9365e9f1054aa991726d731c65aa02";
    private static final String TAG = InstagramListActivity.class.getSimpleName();
    private String searchText;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private InstagramAdapter mInstagramAdapter;
    private List<InstagramData> mInstaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            searchText = extras.getString("SearchParam");
        }

        mRecyclerView = (RecyclerView) this.findViewById(R.id.list);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.swipe);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        mInstagramAdapter = new InstagramAdapter(mInstaList);
        mRecyclerView.setAdapter(mInstagramAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadData();
    }

    private void downloadData() {
        if (!TextUtils.isEmpty(searchText)) {
            RetrofitExample.getGitHubApiClient().getInstaData(searchText, CLIENT_ID, new Callback<InstagramData>() {
                @Override
                public void success(InstagramData instaData, Response response) {
                    Log.d(TAG, response.getUrl());
                    for (int i = 0; i < instaData.instagrams.size(); i++) {
                        Log.d(TAG, searchText + " Link - " + instaData.instagrams.get(i).images.thumbnail.url);
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.d(TAG, "Retrofit failed - " + retrofitError.getMessage());
                }
            });
        }

    }
}
