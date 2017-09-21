package com.example.abhishek.movies.activity;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.RestCallResponseInterface;
import com.example.abhishek.movies.adapter.MovieCastRecyclerAdapter;
import com.example.abhishek.movies.adapter.MovieImageAndNameAdapter;
import com.example.abhishek.movies.adapter.MovieSpecificImageAdapter;
import com.example.abhishek.movies.adapter.UpcomingMovieRecyclerAdapter;
import com.example.abhishek.movies.asyncTask.CustomRequest;
import com.example.abhishek.movies.asyncTask.RestCallController;
import com.example.abhishek.movies.model.CastModel;
import com.example.abhishek.movies.model.CastModels;
import com.example.abhishek.movies.model.CrewModel;
import com.example.abhishek.movies.model.CrewModels;
import com.example.abhishek.movies.model.MovieImage;
import com.example.abhishek.movies.model.MovieImages;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.example.abhishek.movies.model.Trailer;
import com.example.abhishek.movies.utility.SpaceItemDecoration;
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

    private TextView movieGenre;
    private TextView movieName;
    private TextView imageViewAll;

    private ImageView videoPreview;

    private RecyclerView movieCastRecyclerView;
    private  LinearLayoutManager linearLayoutManager;

    private RecyclerView similarMovieRecyclerView;
    private LinearLayoutManager similarLinearLayoutManager;

    private RecyclerView recommendationMoviesRecyclerView;
    private LinearLayoutManager recommendationLinearLayoutManager;

    private RecyclerView movieSpecificImageRecyclerView;
    private LinearLayoutManager movieSpecificImageLinearLayoutManager;

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
        movieName = (TextView) findViewById(R.id.movie_screen_movie_name_textview);
        imageViewAll = (TextView) findViewById(R.id.movie_screen_view_all_textview);
        videoPreview = (ImageView) findViewById(R.id.movie_screen_video_play_image_view);

        movieCastRecyclerView = (RecyclerView) findViewById(R.id.movie_screen_cast_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        movieCastRecyclerView.setLayoutManager(linearLayoutManager);

        similarMovieRecyclerView = (RecyclerView) findViewById(R.id.movie_screen_similar_recycler_view);
        similarLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        similarMovieRecyclerView.setLayoutManager(similarLinearLayoutManager);

        recommendationMoviesRecyclerView = (RecyclerView) findViewById(R.id.movie_screen_recommendations_recycler_view);
        recommendationLinearLayoutManager =  new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recommendationMoviesRecyclerView.setLayoutManager(recommendationLinearLayoutManager);

        movieSpecificImageRecyclerView = (RecyclerView) findViewById(R.id.movie_screen_images_recycler_view);
        movieSpecificImageLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        movieSpecificImageRecyclerView.setLayoutManager(movieSpecificImageLinearLayoutManager);

        // On Click For Widgets
        imageViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            startActivity(new Intent(MovieScreen.this,ImageDemoActivity.class));
            }
        });

        videoPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieScreen.this,VideoPlay.class);
                intent.putExtra("video_detail",movieInfo.getMovieTrailer());
                startActivity(intent);
            }
        });

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
         *  https://api.themoviedb.org/3/movie/190859?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieDetailedURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieDetailedURL);
        CustomRequest customRequest = new CustomRequest(Request.Method.GET, movieDetailedURL, null, this, "MovieInfo", this);
        customRequest.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest);
        /**
         *  Fetching Movie Credits
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/190859/credits?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieCastURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/credits?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieCastURL);
        CustomRequest customRequest1 = new CustomRequest(Request.Method.GET, movieCastURL, null, this, "CastInfo", this);
        customRequest1.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest1);
        /**
         *  Fetching Movie Videos
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/190859/videos?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieVideoURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/videos?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieVideoURL);
        CustomRequest customRequest2 = new CustomRequest(Request.Method.GET, movieVideoURL, null, this, "Videos", this);
        customRequest2.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest2);
        /**
         *  Fetching Movie Images
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/190859/images?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieImageURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/images?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieImageURL);
        CustomRequest customRequest3 = new CustomRequest(Request.Method.GET, movieImageURL, null, this, "Images", this);
        customRequest3.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest3);

        /**
         *  Fetching Similar Movies
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/190859/similar?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieSimilarURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/similar?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieSimilarURL);
        CustomRequest customRequest4 = new CustomRequest(Request.Method.GET, movieSimilarURL, null, this, "Similar", this);
        customRequest4.setRestCallResponseInterface(this);
        RestCallController.getInstance().addToRequestQueue(customRequest4);

        /**
         *  Fetching Recommendation Movies
         *  Sample URL:
         *  https://api.themoviedb.org/3/movie/190859/recommendations?api_key=a0c307832b6d7d9d4fcf069b911f90c0
         */
        String movieRecommendationURL = "https://api.themoviedb.org/3/movie/" + movieInfo.getId() + "/recommendations?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
        Log.e(TAG, movieRecommendationURL);
        CustomRequest customRequest5 = new CustomRequest(Request.Method.GET, movieRecommendationURL, null, this, "Recommendations", this);
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
            processMovieDetail(response);
        } else if (type == "CastInfo") {
            Log.e(TAG, "Cast");
            processCast(response);
        } else if(type == "Recommendations"){
            Log.e(TAG,"Recommendations");
            processRecommendations(response);
        } else if(type == "Videos"){
            Log.e(TAG,"Videos");
            processVideos(response);
        } else if(type == "Images"){
            Log.e(TAG,"Images");
            processImages(response);
        } else if(type == "Similar"){
            Log.e(TAG,"Similar");
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
            Log.e("Images Length",backdrops.length()+", "+posters.length());

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

            // Setting Cast Recycler View
            MovieSpecificImageAdapter adapter = new MovieSpecificImageAdapter(this,movieInfo);
            movieSpecificImageRecyclerView.setAdapter(adapter);
            movieSpecificImageRecyclerView.addItemDecoration(new SpaceItemDecoration(5));

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

            if(results.length()>0){
                videoPreview.setVisibility(View.VISIBLE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processSimilar(JSONObject object) {
        Log.e("Processing Similar","True");
        MovieModels similarMovies = new MovieModels("Similar");
        try {
            JSONArray results = object.getJSONArray("results");
            Log.e(TAG,"Size Of JSON ARRAY"+ results.length());

            similarMovies.setCurrentPage(object.getInt("page"));
            similarMovies.setTotalPage(object.getInt("total_pages"));
            similarMovies.setTotalResults(object.getInt("total_results"));

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
                similarMovies.list.add(movie);
            }

            movieInfo.setSimilarMovies(similarMovies);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Setting Recycler View for the similar Movies
        MovieImageAndNameAdapter similarUpcomingMovieAdapter = new MovieImageAndNameAdapter(this,similarMovies);
        similarMovieRecyclerView.setAdapter(similarUpcomingMovieAdapter);
        similarMovieRecyclerView.addItemDecoration(new SpaceItemDecoration(5));
        Log.e("Processing Similar","Recycler Is Added");
    }

    private void processRecommendations(JSONObject object) {

            MovieModels recommendationMovies = new MovieModels("Recommendations");
            try {
                JSONArray results = object.getJSONArray("results");
                Log.e(TAG,"Size Of JSON ARRAY"+ results.length());

                recommendationMovies.setCurrentPage(object.getInt("page"));
                recommendationMovies.setTotalPage(object.getInt("total_pages"));
                recommendationMovies.setTotalResults(object.getInt("total_results"));

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
                    recommendationMovies.list.add(movie);
                }

                movieInfo.setRecommendationMovies(recommendationMovies);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        // Setting Recycler View for the similar Movies
        MovieImageAndNameAdapter similarUpcomingMovieAdapter = new MovieImageAndNameAdapter(this,recommendationMovies);
        recommendationMoviesRecyclerView.setAdapter(similarUpcomingMovieAdapter);
        recommendationMoviesRecyclerView.addItemDecoration(new SpaceItemDecoration(5));

    }

    private void processMovieDetail(JSONObject response) {
        try {
            movieInfo.setImdbId(response.getString("imdb_id"));
            movieInfo.setRevenue(response.getInt("revenue"));
            movieInfo.setRuntime(response.getInt("runtime"));
            movieInfo.setBudget(response.getInt("budget"));
            JSONArray productionCountries = response.getJSONArray("production_countries");
            JSONArray spokenLanguages = response.getJSONArray("spoken_languages");
            JSONArray genre = response.getJSONArray("genres");

            ArrayList<Pair<String,String>> productionCountriesList = new ArrayList<>();
            ArrayList<Pair<String,String>> spokenLangugaeList = new ArrayList<>();
            ArrayList<Pair<Integer,String>> genreList = new ArrayList<>();
            ArrayList<String> genreName = new ArrayList<>();

            for(int i=0;i<productionCountries.length();i++){
                JSONObject object = productionCountries.getJSONObject(i);
                Pair<String,String> pair = new Pair<>(object.getString("iso_3166_1"),object.getString("name"));
                productionCountriesList.add(pair);
            }

            for(int i=0;i<spokenLanguages.length();i++){
                JSONObject object = spokenLanguages.getJSONObject(i);
                Pair<String,String> pair = new Pair<>(object.getString("iso_639_1"),object.getString("name"));
                spokenLangugaeList.add(pair);
            }

            for(int i=0;i<genre.length();i++){
                JSONObject object = genre.getJSONObject(i);
                Pair<Integer,String> pair = new Pair<>(object.getInt("id"),object.getString("name"));
                genreList.add(pair);
                genreName.add(object.getString("name"));
            }

            movieInfo.setProductionCountries(productionCountriesList);
            movieInfo.setGenre(genreList);
            movieInfo.setSpokenLanguages(spokenLangugaeList);

            movieGenre.setText(android.text.TextUtils.join(",",genreName));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        movieDuration.setText(movieInfo.getRuntime()+" ");



    }

    private void processCast(JSONObject response) {
        try {
            CastModels castModels = new CastModels();
            CrewModels crewModels = new CrewModels();
            JSONArray cast = response.getJSONArray("cast");
            JSONArray crew = response.getJSONArray("crew");
            castModels.setMovieId(response.getInt("id"));

            for(int i=0;i<cast.length();i++){
                JSONObject object = cast.getJSONObject(i);
                CastModel model = new CastModel();
                model.setName(object.optString("name"));
                model.setOrder(object.optInt("order"));
                model.setGender(object.optInt("gender"));
                model.setCreditId(object.optString("credit_id"));
                model.setCharacterName(object.optString("character"));
                model.setCastId(object.optInt("cast_id"));
                model.setId(object.optInt("id"));
                model.setProfilePath(object.optString("profile_path"));
                castModels.addCast(model);
            }
            ArrayList<String> directors = new ArrayList<>();
            ArrayList<String> writers = new ArrayList<>();

            for(int i=0;i<crew.length();i++){
                JSONObject object = crew.getJSONObject(i);
                CrewModel crewModel = new CrewModel();
                crewModel.setCreditId(object.optString("credit_id"));
                crewModel.setDepartment(object.optString("department"));
                crewModel.setGender(object.optInt("gender"));
                crewModel.setId(object.optInt("id"));
                crewModel.setJob(object.optString("job"));
                crewModel.setName(object.optString("name"));
                crewModel.setProfile_path(object.optString("profile_path"));
                crewModels.addCrew(crewModel);

                if(crewModel.getJob().equals("Director")){
                    directors.add(crewModel.getName());
                }
                else if(crewModel.getJob().equals("Writer")){
                    writers.add(crewModel.getName());
                }

            }

            movieInfo.setMovieCast(castModels);
            movieInfo.setMovieCrew(crewModels);



            // Setting Text for Directors and Movie Text View
            movieDirector.setText(android.text.TextUtils.join(",",directors));
            movieWriter.setText(android.text.TextUtils.join(",",writers));

            // Setting Cast Recycler View
            MovieCastRecyclerAdapter adapter = new MovieCastRecyclerAdapter(this,movieInfo.getMovieCast());
            movieCastRecyclerView.setAdapter(adapter);
            movieCastRecyclerView.addItemDecoration(new SpaceItemDecoration(5));


        } catch (JSONException e) {
            System.out.println("Error " + e.getMessage());
        }


    }

}
