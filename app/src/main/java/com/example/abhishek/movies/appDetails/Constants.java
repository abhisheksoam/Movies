package com.example.abhishek.movies.appDetails;

import java.util.HashMap;

/**
 * Created by abhishek on 08-01-2016.
 */


public class Constants {


    private final String API_KEY = "?api_key=a0c307832b6d7d9d4fcf069b911f90c0";
    private final String BASE_URL = "://api.themoviedb.org/";
    private final String SSL = "https";
    private final String NO_SSL = "http";
    private final String API_VERSION = "3/";
    private static String mainServer = "https://api.themoviedb.org/";
    /**
     *  URL String for getting Popular URL
     */
    private static final String POPULAR = "https://api.themoviedb.org/3/movie/popular?api_key=a0c307832b6d7d9d4fcf069b911f90c0";

    /**
     *  URL String for getting upcoming movies
     */
    private static final String UPCOMING = "https://api.themoviedb.org/3/movie/upcoming?api_key=a0c307832b6d7d9d4fcf069b911f90c0";

    /**
     *  URL String for getting now playing movie
     */
    private static final String NOW_PLAYING = "https://api.themoviedb.org/3/movie/now_playing?api_key=a0c307832b6d7d9d4fcf069b911f90c0";



    // HashMap For Genre
    HashMap<Integer,String> map = new HashMap<>();


    //https://api.themoviedb.org/3/search/movie?api_key=a0c307832b6d7d9d4fcf069b911f90c0&query=Dil Chahta
    // For Search
    private static final String SEARCH_MOVIE = "search/movie";

    public static String getSEARCH() {
        return SEARCH;
    }

    private static final String SEARCH = mainServer+ "search/multi/?api_key=a0c307832b6d7d9d4fcf069b911f90c0&query=";
    //For Searching Movies , TV Shows , Person
    private static final String SEARCH_EVERYTHING = "search/multi";


    //http://imdb.wemakesites.net/api/tt0110076

    //http://image.tmdb.org/t/p/w300/pZVL7yJdKqR7oAa4J8fUUOwuiGG.jpg

    //For Poster Path
    private final String POSTERPATH_BASE_URL = "://image.tmdb.org/t/p/";
    private final String POSTER_WIDTH = "w300";


    //https://www.youtube.com/watch?v=enJYNuWBJ9g // source code will be provided
    // For trailers
    private final String YOUTUBE_TRAILER_BASEURL = "https://www.youtube.com/watch?v=";

    //For Searching Trailer      // movie/id/trailers
    private final String SEARCH_TRAILERS = "movie/{id}/trailers";


    // API key for YouTube API
    public static final String DEVELOPER_KEY = "AIzaSyDDACz2R7Js8aQZ0xHGbfCra46qQBEDmyU";

    // For BollyWood movies
    public static final String AUTH_TOKEN="290A700988F8E7F5C66944B4083607AD";


    protected void setMap() {
        map.put(28,"Action" );
        map.put(12,"Adventure" );
        map.put( 16,"Animation");
        map.put(35,"Comedy");
        map.put(80,"Crime");
        map.put(99,"Documentary");
        map.put(18,"Drama");
        map.put(10751,"Family");
        map.put(14,"Fantasy");
        map.put(10769,"Foreign");
        map.put(36,"History");
        map.put(27,"Horror");
        map.put(10402,"Music");
        map.put(9648,"Mystery");
        map.put(10749,"Romance");
        map.put(878,"Science Fiction");
        map.put(10770,"TV Movie");
        map.put(53,"Thriller");
        map.put(10752,"War");
        map.put(37,"Western");
    }

    /**
     *
     * @return POPULAR
     */
    public static String getPOPULAR() {
        return POPULAR;
    }

    /**
     *
     * @return UPCOMING
     */
    public static String getUPCOMING() {
        return UPCOMING;
    }

    /**
     *
     * @return NOW_PLAYING
     */
    public static String getNowPlaying() {
        return NOW_PLAYING;
    }

    public HashMap<Integer,String> getMap() {
        return map;
    }

    public static String getSearchEverything() {
        return SEARCH_EVERYTHING;
    }

    public String getPOSTER_WIDTH() {
        return POSTER_WIDTH;
    }

    public String getPOSTERPATH_BASE_URL() {
        return POSTERPATH_BASE_URL;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public String getSSL() {
        return SSL;
    }

    public String getApiVersion() {
        return API_VERSION;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getSearchMovie() {
        return SEARCH_MOVIE;
    }

    public String getNO_SSL() {
        return NO_SSL;
    }
}


