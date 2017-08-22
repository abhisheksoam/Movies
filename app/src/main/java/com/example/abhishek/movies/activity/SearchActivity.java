package com.example.abhishek.movies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.onRecyclerItemClick;
import com.example.abhishek.movies.adapter.SearchAdapter;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.example.abhishek.movies.utility.ItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


/**
 * Created by abhishek on 28/07/17.
 */

public class SearchActivity extends AppCompatActivity implements onRecyclerItemClick {
    public static final String TAG = "SEARCH_ACTIVITY";
    private String MOVIE_TYPE = "SEARCH";

    private RecyclerView searchRecylerView;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String jsonString;

    private MovieModels searchedMovies;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "On create");
        setContentView(R.layout.search_screen);
        handleIntent(getIntent());
        init();

    }

    private void init() {
        searchRecylerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        if(searchedMovies.list.size()>0){
            searchAdapter = new SearchAdapter(this,searchedMovies);
            searchAdapter.setOnRecyclerItemClick(this);
            searchRecylerView.setAdapter(searchAdapter);
            searchRecylerView.setLayoutManager(linearLayoutManager);
            searchRecylerView.addItemDecoration(new ItemDecoration(this) {
            });

        }
    }


    private void handleIntent(Intent intent) {
        if (intent == null) return;
        jsonString = intent.getStringExtra("json_string");
        prepareSearchData(jsonString);
    }

    private void prepareSearchData(String jsonString) {
        Log.e(TAG, "Pre-processing MovieModels");
        if(jsonString.isEmpty())return;

        Log.e(TAG,jsonString);
        JSONObject object = null;
        try {
            object = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        searchedMovies = new MovieModels(MOVIE_TYPE);
        try {
            JSONArray results = object.getJSONArray("results");
            Log.e(TAG, "Size Of JSON ARRAY" + results.length());

            searchedMovies.setCurrentPage(object.getInt("page"));
            searchedMovies.setTotalPage(object.getInt("total_pages"));
            searchedMovies.setTotalResults(object.getInt("total_results"));

            for (int i = 0; i < results.length(); i++) {
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

                for (int j = 0; j < genreArray.length(); j++) {
                    movie.genreIds.add(genreArray.getInt(j));
                }
                searchedMovies.list.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e(TAG,"On back pressed");

    }

    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(this, MovieScreen.class);
        intent.putExtra("MovieDetail",searchedMovies.list.get(position));

        startActivity(intent);
    }
}
