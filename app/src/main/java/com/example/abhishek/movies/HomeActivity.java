package com.example.abhishek.movies;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.abhishek.movies._interface.CustomAsyncInterface;
import com.example.abhishek.movies.activity.SearchActivity;
import com.example.abhishek.movies.activity.UniversalDrawerActivity;
import com.example.abhishek.movies.adapter.HomeViewPagerAdapter;
//import com.example.abhishek.movies.adapter.UpcomingMovieRecyclerAdapter;
import com.example.abhishek.movies.appDetails.Constants;
import com.example.abhishek.movies.asyncTask.CustomAsyncTask;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.example.abhishek.movies.utility.HorizontalItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HomeActivity extends AppCompatActivity implements CustomAsyncInterface {


    /**
     * Variables
     */
    private static String TAG = "HOME ACTIVITY";

    /**
     *  Widgets Variable Declaration
     */
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout smartTabLayout;
    private ProgressDialog progressDialog;

    /**
     *
     */
    HomeViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"On Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        handleIntent(getIntent());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        smartTabLayout = (TabLayout) findViewById(R.id.tabs);
        smartTabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_activity_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) HomeActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(HomeActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);

    }

    private void handleIntent(Intent intent){
        Log.e(TAG,"Inside Handle Intent");
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.e(TAG,"Action search "+ query);
            callTask(query);
        }

    }

    private void callTask(String query) {
        CustomAsyncTask searchAsyncTask = new CustomAsyncTask();
        searchAsyncTask.setContext(this);
        searchAsyncTask.setCustomAsyncInterface(this);
        searchAsyncTask.execute(Constants.getSEARCH()+query,"SEARCH");
    }


    @Override
    public void onDataReceived(String data, String type) {
        Log.e(TAG,"API sting "+ data);
        if(type.equals("SEARCH")){
            Intent intent = new Intent();
            intent.putExtra("json_string",data);
            intent.setClass(this, SearchActivity.class);
            startActivity(intent);
        }
    }
}
