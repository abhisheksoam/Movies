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


public class HomeActivity extends AppCompatActivity implements CustomAsyncInterface {

    private static String UPCOMING_KEYWORD = "UPCOMING";
    private static String NOW_PLAYING_KEYWORD = "NOW PLAYING";
    private static String POPULAR_KEYWORD = "POPULAR";
    private static String TAG = "HOME ACTIVITY";
    /**
     * Variables
     */
    private MovieModels upcomingMovies;
    private MovieModels popularMovies;
    private MovieModels nowPlayingMovies;

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

        callTask();

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
//        View view = navigationView.inflateHeaderView(R.layout.navigation_header);
//        TextView textview = (TextView) view.findViewById(R.id.username);



    }

    private void callTask() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait!");
        progressDialog.show();
        CustomAsyncTask upcomingAsyncTask = new CustomAsyncTask();
        upcomingAsyncTask.setContext(this);
        upcomingAsyncTask.setCustomAsyncInterface(this);
        upcomingAsyncTask.execute(Constants.getUPCOMING(),UPCOMING_KEYWORD);

        CustomAsyncTask nowPlayingAsyncTask = new CustomAsyncTask();
        nowPlayingAsyncTask.setContext(this);
        nowPlayingAsyncTask.setCustomAsyncInterface(this);
        nowPlayingAsyncTask.execute(Constants.getNowPlaying(),NOW_PLAYING_KEYWORD);

        CustomAsyncTask popularAsyncTask = new CustomAsyncTask();
        popularAsyncTask.setContext(this);
        popularAsyncTask.setCustomAsyncInterface(this);
        popularAsyncTask.execute(Constants.getPOPULAR(),POPULAR_KEYWORD);

    }

    @Override
    public void onDataReceived(String data,String type) {
        Log.e(TAG,data.toString());
        if(data!=null){
            storeData(data,type);
            if(upcomingMovies != null && popularMovies != null && nowPlayingMovies != null){
                progressDialog.dismiss();

                adapter = new HomeViewPagerAdapter(this);
                adapter.setNowplayingMovies(nowPlayingMovies);
                adapter.setPopularMovies(popularMovies);
                adapter.setUpcomingMovies(upcomingMovies);
                viewPager.setAdapter(adapter);
                smartTabLayout = (TabLayout) findViewById(R.id.tabs);
                smartTabLayout.setupWithViewPager(viewPager);
            }
        }
    }

    private void storeData(String data, String type) {
        JSONObject object = null;
        try {
            object = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(type.equals(UPCOMING_KEYWORD)){
            upcomingMovies = new MovieModels(type);
            try {
                JSONArray results = object.getJSONArray("results");
                upcomingMovies.setCurrentPage(object.getInt("page"));
                upcomingMovies.setTotalPage(object.getInt("total_pages"));
                upcomingMovies.setTotalResults(object.getInt("total_results"));

                for(int i=0;i<results.length();i++){
                    JSONObject obj = results.getJSONObject(i);
                    MovieModel movie = new MovieModel();
                    movie.setAdult(obj.optString("adult"));
                    movie.setBackdropPath(obj.optString("backdrop_path"));
                    movie.setId(obj.optInt("id"));
                    movie.setOriginalLanguage(obj.optString("original_language"));
                    movie.setPopularity(obj.optInt("popularity"));
                    movie.setOverview(obj.optString("overview"));
                    movie.setReleaseDate(obj.optString("release_date"));
                    movie.setTitle(obj.optString("title"));
                    movie.setOriginalTitle(obj.optString("original_title"));
                    movie.setPosterPath(obj.optString("poster_path"));
                    movie.setVoteAverage(obj.optInt("vote_average"));
                    movie.setVoteCount(obj.optInt("vote_count"));

                    JSONArray genreArray = obj.optJSONArray("genre_ids");
                    for(int j=0;j<obj.length();j++){
                        movie.genreIds.add(genreArray.getInt(j));
                    }
                    upcomingMovies.list.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if(type.equals(POPULAR_KEYWORD)){
            popularMovies = new MovieModels(type);
            try {
                JSONArray results = object.getJSONArray("results");
                popularMovies.setCurrentPage(object.getInt("page"));
                popularMovies.setTotalPage(object.getInt("total_pages"));
                popularMovies.setTotalResults(object.getInt("total_results"));

                for(int i=0;i<results.length();i++){
                    JSONObject obj = results.getJSONObject(i);
                    MovieModel movie = new MovieModel();
                    movie.setAdult(obj.optString("adult"));
                    movie.setBackdropPath(obj.optString("backdrop_path"));
                    movie.setId(obj.optInt("id"));
                    movie.setOriginalLanguage(obj.optString("original_language"));
                    movie.setPopularity(obj.optInt("popularity"));
                    movie.setOverview(obj.optString("overview"));
                    movie.setReleaseDate(obj.optString("release_date"));
                    movie.setTitle(obj.optString("title"));
                    movie.setOriginalTitle(obj.optString("original_title"));
                    movie.setPosterPath(obj.optString("poster_path"));
                    movie.setVoteAverage(obj.optInt("vote_average"));
                    movie.setVoteCount(obj.optInt("vote_count"));

                    JSONArray genreArray = obj.optJSONArray("genre_ids");
                    for(int j=0;j<obj.length();j++){
                        movie.genreIds.add(genreArray.getInt(j));
                    }
                    popularMovies.list.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if(type.equals(NOW_PLAYING_KEYWORD)){
            nowPlayingMovies = new MovieModels(type);
            try {
                JSONArray results = object.getJSONArray("results");
                nowPlayingMovies.setCurrentPage(object.getInt("page"));
                nowPlayingMovies.setTotalPage(object.getInt("total_pages"));
                nowPlayingMovies.setTotalResults(object.getInt("total_results"));

                for(int i=0;i<results.length();i++){
                    JSONObject obj = results.getJSONObject(i);
                    MovieModel movie = new MovieModel();
                    movie.setAdult(obj.optString("adult"));
                    movie.setBackdropPath(obj.optString("backdrop_path"));
                    movie.setId(obj.optInt("id"));
                    movie.setOriginalLanguage(obj.optString("original_language"));
                    movie.setPopularity(obj.optInt("popularity"));
                    movie.setOverview(obj.optString("overview"));
                    movie.setReleaseDate(obj.optString("release_date"));
                    movie.setTitle(obj.optString("title"));
                    movie.setOriginalTitle(obj.optString("original_title"));
                    movie.setPosterPath(obj.optString("poster_path"));
                    movie.setVoteAverage(obj.optInt("vote_average"));
                    movie.setVoteCount(obj.optInt("vote_count"));

                    JSONArray genreArray = obj.optJSONArray("genre_ids");
                    for(int j=0;j<obj.length();j++){
                        movie.genreIds.add(genreArray.getInt(j));
                    }
                    nowPlayingMovies.list.add(movie);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
