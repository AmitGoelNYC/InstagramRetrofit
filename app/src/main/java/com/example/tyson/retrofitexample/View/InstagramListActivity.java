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

import com.example.tyson.retrofitexample.Controller.Controller;
import com.example.tyson.retrofitexample.Model.InstagramAdapter;
import com.example.tyson.retrofitexample.Model.InstagramData;
import com.example.tyson.retrofitexample.R;
import com.example.tyson.retrofitexample.RetrofitExample;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InstagramListActivity extends AppCompatActivity implements Controller.InstagramCallbackListener{

    //private static final String USER = "AMITGOELNYC";
    private String searchText;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private InstagramAdapter mInstagramAdapter;
    private List<InstagramData.instagramclass> mInstaList = new ArrayList<>();
    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mController = new Controller(this);
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

        mController.startFetching(searchText);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching(searchText);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onFetchProgress(List<InstagramData.instagramclass> instagramclass) {
        mInstagramAdapter.setList(instagramclass);
    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }
}
