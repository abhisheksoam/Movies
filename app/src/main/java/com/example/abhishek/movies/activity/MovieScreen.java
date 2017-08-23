package com.example.abhishek.movies.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.abhishek.movies.model.CastModel;
import com.example.abhishek.movies.model.CastModels;
import com.example.abhishek.movies.model.MovieImage;
import com.example.abhishek.movies.model.MovieImages;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.Trailer;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MovieScreen extends AppCompatActivity implements Response.ErrorListener, RestCallResponseInterface {
    private static final String TAG = "MOVIE SCREEN";
    private MovieModel movieInfo;
    private ImageView movieCoverImage;
    private TextView movieDuration;
    private TextView movieReleaseDate;
    private TextView movieMetaScore;
    private TextView movieDirector;
    private TextView movieWriter;
    private TextView movieSynopsis;
    private RecyclerView movieCastRecyclerView;
    private TextView movieAwardsAndNomination;
    private TextView movieGenre;
    private TextView movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_screen);
        initiliazeWidgets();
        Intent intent = getIntent();
        /**
         *  If intent is not null when the activity is created first time
         *  It gets the value from the intent
         */
        if (intent != null) {
            movieInfo = (MovieModel) intent.getSerializableExtra("MovieDetail");
            Log.e(TAG, movieInfo.getId() + " ");
            getMovieRelatedData();
            setObjectsOnMovieScreen();
        }
        /**
         *  If intent is null, something unexpected occured
         */
        else{

        }



    }

    private void initiliazeWidgets() {
        movieCoverImage = (ImageView) findViewById(R.id.movie_screen_image_thumbnail);
        movieDuration = (TextView) findViewById(R.id.movie_activity_movie_duration);
        movieReleaseDate = (TextView) findViewById(R.id.movie_activity_release_date);
        movieMetaScore = (TextView) findViewById(R.id.movie_screen_metascore);
        movieDirector = (TextView) findViewById(R.id.movie_screen_director_textview);
        movieWriter = (TextView) findViewById(R.id.movie_screen_writer_textview);
        movieSynopsis = (TextView) findViewById(R.id.movie_screen_synopsis_textview);
        movieGenre = (TextView) findViewById(R.id.movie_screen_genre_textview);
        movieAwardsAndNomination = (TextView) findViewById(R.id.movie_screen_awards_and_nomination_textview);
        movieCastRecyclerView = (RecyclerView) findViewById(R.id.movie_screen_cast_recycler_view);
        movieName = (TextView) findViewById(R.id.movie_screen_movie_name_textview);

    }

    /**
     * Initially set the screen with the present information about a movie
     */
    private void setObjectsOnMovieScreen() {
        // Downloading Movie Thumbnail and attaching it to imageview
        Log.e(TAG,"Movie Thumbnail URL: "+"http://image.tmdb.org/t/p/w1000" + movieInfo.getPosterPath());
        Picasso.with(this).load("http://image.tmdb.org/t/p/w780" + movieInfo.getPosterPath()).into(movieCoverImage);

        // Date in format ex:(9 Jun 2016)
        String releaseDate = movieInfo.getReleaseDate();
        int monthNumber = Integer.parseInt(releaseDate.substring(5,7));
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        cal.set(Calendar.MONTH,monthNumber);
        String month_name = month_date.format(cal.getTime());
        String year = releaseDate.substring(0,4);
        String date = releaseDate.substring(8,releaseDate.length());
        int day = Integer.parseInt(date);
        movieReleaseDate.setText(day+" "+month_name.substring(0,3)+" "+year);

        movieSynopsis.setText(movieInfo.getOverview());
        movieName.setText(movieInfo.getOriginalTitle());


    }

    /**
     *  Calls various Tasks parallel to fetch data for a movie 
     */
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
            processMovieDetail(response);
        } else if (type == "CastInfo") {
            Log.e(TAG, "Cast");
            Log.e(TAG, response.toString());
            processCast(response);
        } else if(type == "Recommendations"){
            Log.e(TAG,"Recommendations");
            Log.e(TAG, response.toString());
            processRecommendations(response);
        } else if(type == "Videos"){
            Log.e(TAG,"Videos");
            Log.e(TAG, response.toString());
            processVideos(response);
        } else if(type == "Images"){
            Log.e(TAG,"Images");
            Log.e(TAG, response.toString());
            processImages(response);
        } else if(type == "Similar"){
            Log.e(TAG,"Similar");
            Log.e(TAG, response.toString());
            processSimilar(response);
        }

    }

    private void processImages(JSONObject response) {
        MovieImages movieImages = new MovieImages();
        ArrayList<MovieImage> backdropList = new ArrayList<>();
        ArrayList<MovieImage> posterList = new ArrayList<>();
        try {
            Integer movieId = response.getInt("id") ;
            JSONArray backdrops = response.getJSONArray("backdrops");
            JSONArray posters = response.getJSONArray("posters");

            for(int i=0;i<backdrops.length();i++){
                JSONObject object = backdrops.getJSONObject(i);
                MovieImage movieImage = new MovieImage();
                movieImage.setFilePath(object.getString("file_path"));
                movieImage.setAspectRatio(object.getInt("aspect_ratio"));
                movieImage.setHeight(object.getInt("height"));
                movieImage.setWidth(object.getInt("width"));
                backdropList.add(movieImage);
            }

            for(int i=0;i<posters.length();i++){
                JSONObject object = posters.getJSONObject(i);
                MovieImage movieImage = new MovieImage();
                movieImage.setFilePath(object.getString("file_path"));
                movieImage.setAspectRatio(object.getInt("aspect_ratio"));
                movieImage.setHeight(object.getInt("height"));
                movieImage.setWidth(object.getInt("width"));
                posterList.add(movieImage);
            }
            // Setting MovieImages Model
            movieImages.setMovieId(movieId);
            movieImages.setBackdropPaths(backdropList);
            movieImages.setPosterPath(posterList);

            // Setting MovieInfo Model
            movieInfo.setMovieImages(movieImages);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processVideos(JSONObject response) {
        try {
            JSONArray results = response.getJSONArray("results");
            for(int i=0;i<results.length();i++){
                JSONObject object = results.getJSONObject(i);
                Trailer trailer = new Trailer();
                trailer.setId(object.getString("id"));
                trailer.setIso_639_1(object.getString("iso_639_1"));
                trailer.setIso_3166_1(object.getString("iso_3166_1"));
                trailer.setKey(object.getString("key"));
                trailer.setName(object.getString("name"));
                trailer.setSite(object.getString("site"));
                trailer.setSize(object.getInt("size"));
                trailer.setType(object.getString("type"));
                movieInfo.getMovieTrailer().add(trailer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processSimilar(JSONObject response) {
    }

    private void processRecommendations(JSONObject response) {

    }

    private void processMovieDetail(JSONObject response) {
        try {
            movieInfo.setImdbId(response.getString("imdb_id"));
            movieInfo.setRevenue(response.getInt("revenue"));
            movieInfo.setRuntime(response.getInt("runtime"));
            movieInfo.setBudget(response.getInt("budget"));
            JSONArray productionCountries = response.getJSONArray("production_countries");
            JSONArray spokenLanguages = response.getJSONArray("spoken_language");
            JSONArray genre = response.getJSONArray("genre");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processCast(JSONObject response) {
        try {
            CastModels castModels = new CastModels();
            JSONArray cast = response.getJSONArray("cast");
            castModels.setMovieId(response.getInt("id"));
            for(int i=0;i<cast.length();i++){
                JSONObject object = cast.getJSONObject(i);
                CastModel model = new CastModel();
                model.setName(object.getString("name"));
                model.setOrder(object.getInt("order"));
                model.setGender(object.getInt("gender"));
                model.setCreditId(object.getString("credit_id"));
                model.setCharacterName(object.getString("character"));
                model.setCastId(object.getInt("cast_id"));
                model.setId(object.getInt("id"));
                model.setProfilePath(object.getString("profile_path"));
                castModels.addCast(model);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
