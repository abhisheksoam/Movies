package com.example.abhishek.movies.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.RestCallResponseInterface;
import com.example.abhishek.movies.asyncTask.CustomRequest;
import com.example.abhishek.movies.asyncTask.RestCallController;
import com.example.abhishek.movies.model.MovieModel;

import org.json.JSONObject;

public class MovieScreen extends AppCompatActivity implements Response.ErrorListener, RestCallResponseInterface {
    private static final String TAG = "MOVIE SCREEN";
    MovieModel movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_screen);

        Intent intent = getIntent();
        /**
         *  If intent is not null when the activity is created first time
         *  It gets the value from the intent
         */
        if (intent != null) {
            movieInfo = (MovieModel) intent.getSerializableExtra("MovieDetail");
            Log.e(TAG, movieInfo.getId() + " ");
            getMovieRelatedData();
        }
        /**
         *  If intent is null, something unexpected occured
         */
        else{

        }



    }

    private void getMovieRelatedData() {
        /**
         *  Fetching Extra Movie Detail
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/{movieId}?api_key=xyz
         */
        String movieDetailedURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieDetailedURL);
        CustomRequest customRequest = new CustomRequest(Request.Method.GET, movieDetailedURL, null, this, "MovieInfo", this);
        customRequest.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest);
        /**
         *  Fetching Extra Movie Detail
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/190859?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieCastURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/credits?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieCastURL);
        CustomRequest customRequest1 = new CustomRequest(Request.Method.GET, movieCastURL, null, this, "CastInfo", this);
        customRequest1.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest1);

        String movieVideoURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/videos?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieVideoURL);
        CustomRequest customRequest2 = new CustomRequest(Request.Method.GET, movieCastURL, null, this, "Videos", this);
        customRequest2.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest2);

        String movieImageURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/images?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieImageURL);
        CustomRequest customRequest3 = new CustomRequest(Request.Method.GET, movieCastURL, null, this, "Images", this);
        customRequest3.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest3);

        String movieSimilarURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/similar?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieSimilarURL);
        CustomRequest customRequest4 = new CustomRequest(Request.Method.GET, movieCastURL, null, this, "Similar", this);
        customRequest4.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest4);

        String movieRecommendationURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/recommendations?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieRecommendationURL);
        CustomRequest customRequest5 = new CustomRequest(Request.Method.GET, movieCastURL, null, this, "Recommendations", this);
        customRequest5.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest5);



    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }


    @Override
    public void onResponse(String type, JSONObject response) {
        if (type == "MovieInfo") {
            Log.e(TAG, "Movie Detail");
            Log.e(TAG, response.toString());
        } else if (type == "CastInfo") {
            Log.e(TAG, "Cast");
            Log.e(TAG, response.toString());
        } else if(type == "Recommendations"){
            Log.e(TAG,"Recommendations");
            Log.e(TAG, response.toString());
        } else if(type == "Videos"){
            Log.e(TAG,"Videos");
            Log.e(TAG, response.toString());
        } else if(type == "Images"){
            Log.e(TAG,"Images");
            Log.e(TAG, response.toString());
        } else if(type == "Similar"){
            Log.e(TAG,"Similar");
            Log.e(TAG, response.toString());
        }

    }
}
