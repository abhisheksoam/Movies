package com.example.abhishek.movies.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.CustomAsyncInterface;
import com.example.abhishek.movies._interface.RestCallResponseInterface;
import com.example.abhishek.movies.adapter.UpcomingMovieRecyclerAdapter;
import com.example.abhishek.movies.appDetails.Constants;
import com.example.abhishek.movies.asyncTask.CustomAsyncTask;
import com.example.abhishek.movies.asyncTask.CustomRequest;
import com.example.abhishek.movies.asyncTask.RestCallController;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.example.abhishek.movies.utility.SpaceItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by abhishek on 16/07/17.
 */

public class PopularMovieFragment extends Fragment implements  Response.ErrorListener, RestCallResponseInterface {

    // Variables
    MovieModels popularMovies;
    private static String POPULAR_KEYWORD = "POPULAR";
    private static String TAG = "POPULAR FRAGMENT";

    // Widgets
    private RecyclerView popularRecyclerView;
    private UpcomingMovieRecyclerAdapter popularMoviesAdapter;
    private GridLayoutManager linearLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_popular_movie,container,false);

        return v;
    }

    private void initWidget() {
        popularRecyclerView = (RecyclerView) getActivity().findViewById(R.id.popular_recycler_view);
        linearLayoutManager = new GridLayoutManager(getActivity(),2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initWidget();
        callTask();
    }

    private void callTask() {
        CustomRequest customRequest = new CustomRequest(Request.Method.GET,Constants.getPOPULAR(),null,this,POPULAR_KEYWORD,getActivity());
        customRequest.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest);
    }

    public void onDataReceived(String data, String type) {
        Log.e(TAG,"On Data Received");
        JSONObject object = null;
        try {
            object = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(type.equals(POPULAR_KEYWORD)){
            popularMovies = new MovieModels(type);
            try {
                JSONArray results = object.getJSONArray("results");
                Log.e(TAG,"Size Of JSON ARRAY"+ results.length());

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

                    for(int j=0;j<genreArray.length();j++){
                        movie.genreIds.add(genreArray.getInt(j));
                    }
                    popularMovies.list.add(movie);
                }

                Log.e(TAG,"Size Of Movies list"+ popularMovies.list.size());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            popularMoviesAdapter = new UpcomingMovieRecyclerAdapter(getActivity(),popularMovies);
            popularRecyclerView.setAdapter(popularMoviesAdapter);
            popularRecyclerView.setLayoutManager(linearLayoutManager);
            popularRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String type, JSONObject response) {
        Log.e(TAG,"Popular movie response arrived");
        onDataReceived(response.toString(),type);
    }
}
