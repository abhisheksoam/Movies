package com.example.abhishek.movies;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.abhishek.movies._interface.CustomAsyncInterface;
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


public class HomeActivity extends AppCompatActivity  {



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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ActionBar actionBar =  getSupportActionBar();
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        smartTabLayout = (TabLayout) findViewById(R.id.tabs);
        smartTabLayout.setupWithViewPager(viewPager);



    }

}
