package com.example.abhishek.movies.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.CustomAsyncInterface;
import com.example.abhishek.movies.adapter.UpcomingMovieRecyclerAdapter;
import com.example.abhishek.movies.appDetails.Constants;
import com.example.abhishek.movies.asyncTask.CustomAsyncTask;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.example.abhishek.movies.utility.SpaceItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by abhishek on 17/07/17.
 */

public class UpcomingMovieFragment extends android.support.v4.app.Fragment implements CustomAsyncInterface {
    // Variables
    MovieModels upcomingMovies;
    private static String UPCOMING_KEYWORD = "UPCOMING";
    private static String TAG = "UPCOMING FRAGMENT";

    // Widgets
    private RecyclerView upcomingRecyclerView;
    private UpcomingMovieRecyclerAdapter upcomingMoviesAdapter;
    private GridLayoutManager gridLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_upcoming_movie,container,false);

        return v;
    }

    private void initWidget() {
        upcomingRecyclerView = (RecyclerView) getActivity().findViewById(R.id.upcoming_recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWidget();
        callTask();
    }

    private void callTask() {
        CustomAsyncTask upcomingAsyncTask = new CustomAsyncTask();
        upcomingAsyncTask.setContext(getActivity());
        upcomingAsyncTask.setCustomAsyncInterface(this);
        upcomingAsyncTask.execute(Constants.getUPCOMING(),UPCOMING_KEYWORD);
    }

    @Override
    public void onDataReceived(String data, String type) {
        Log.e(TAG,"On Data Received");
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
                Log.e(TAG,"Size Of JSON ARRAY"+ results.length());

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

                    for(int j=0;j<genreArray.length();j++){
                        movie.genreIds.add(genreArray.getInt(j));
                    }
                    upcomingMovies.list.add(movie);
                }

                Log.e(TAG,"Size Of Movies list"+ upcomingMovies.list.size());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            upcomingMoviesAdapter = new UpcomingMovieRecyclerAdapter(getActivity(), upcomingMovies);
            upcomingRecyclerView.setAdapter(upcomingMoviesAdapter);
            upcomingRecyclerView.setLayoutManager(gridLayoutManager);
            upcomingRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
        }
    }

}
