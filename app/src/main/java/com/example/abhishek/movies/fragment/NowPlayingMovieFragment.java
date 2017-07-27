package com.example.abhishek.movies.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class NowPlayingMovieFragment extends Fragment implements CustomAsyncInterface {
    // Variables
    MovieModels nowPlayingMovies;
    private static String NOW_PLAYING_KEYWORD = "NOW_PLAYING";
    private static String TAG = "NOW_PLAYING_FRAGMENT";

    // Widgets
    private RecyclerView nowPlayingRecyclerView;
    private UpcomingMovieRecyclerAdapter nowPlayingMoviesAdapter;
    private GridLayoutManager gridLayoutManager;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_now_playing_movie,container,false);

        return v;
    }
    private void initWidget() {
        nowPlayingRecyclerView = (RecyclerView) getActivity().findViewById(R.id.now_playing_recycler_view);
        gridLayoutManager = new GridLayoutManager(getActivity(),2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWidget();
        callTask();
    }

    private void callTask() {
        CustomAsyncTask nowPlayingAsyncTask = new CustomAsyncTask();
        nowPlayingAsyncTask.setContext(getActivity());
        nowPlayingAsyncTask.setCustomAsyncInterface(this);
        nowPlayingAsyncTask.execute(Constants.getNowPlaying(), NOW_PLAYING_KEYWORD);
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
        if(type.equals(NOW_PLAYING_KEYWORD)){
            nowPlayingMovies = new MovieModels(type);
            try {
                JSONArray results = object.getJSONArray("results");
                Log.e(TAG,"Size Of JSON ARRAY"+ results.length());

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

                    for(int j=0;j<genreArray.length();j++){
                        movie.genreIds.add(genreArray.getInt(j));
                    }
                    nowPlayingMovies.list.add(movie);
                }

                Log.e(TAG,"Size Of Movies list"+ nowPlayingMovies.list.size());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            nowPlayingMoviesAdapter = new UpcomingMovieRecyclerAdapter(getActivity(), nowPlayingMovies);
            nowPlayingRecyclerView.setAdapter(nowPlayingMoviesAdapter);
            nowPlayingRecyclerView.setLayoutManager(gridLayoutManager);
            nowPlayingRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
        }
    }
}
