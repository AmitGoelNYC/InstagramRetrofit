package com.example.tyson.retrofitexample.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.tyson.retrofitexample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void callInsta(View view) {
        String searchText = ((EditText)findViewById(R.id.searchText)).getText().toString();
        if(!TextUtils.isEmpty(searchText)){
            Intent intent = new Intent(this,InstagramListActivity.class);
            intent.putExtra("SearchParam", searchText);
            startActivity(intent);
        }
    }
}
