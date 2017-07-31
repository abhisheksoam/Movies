package com.example.abhishek.movies.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.abhishek.movies.HomeActivity;
import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.CustomAsyncInterface;
import com.example.abhishek.movies._interface.onRecyclerItemClick;
import com.example.abhishek.movies.adapter.SearchAdapter;
import com.example.abhishek.movies.appDetails.Constants;
import com.example.abhishek.movies.asyncTask.CustomAsyncTask;

/**
 * Created by abhishek on 28/07/17.
 */

public class SearchActivity extends AppCompatActivity implements CustomAsyncInterface,onRecyclerItemClick {
    public static final String TAG = "SEARCH_ACTIVITY";

    /**
     *  Default Search: Set to Multi
     */

    /**
     *Widgets
     */

    private RecyclerView searchRecylerView;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"On create") ;
        setContentView(R.layout.search_screen);
        handleIntent(getIntent());
        init();

    }

    private void init() {
        searchRecylerView = (RecyclerView) findViewById(R.id.search_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
    }

    private void handleIntent(Intent intent){
        Log.e(TAG,"Inside Handle Intent");
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.e(TAG,query);
            callTask(query);
        }
    }

    private void callTask(String query) {
        CustomAsyncTask searchAsyncTask = new CustomAsyncTask();
        searchAsyncTask.setContext(this);
        searchAsyncTask.setCustomAsyncInterface(this);
        searchAsyncTask.execute(Constants.getSEARCH(),TAG);
    }

    @Override
    public void onDataReceived(String data, String type) {

    }

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(this,MovieScreen.class);
        startActivity(intent);
    }
}
