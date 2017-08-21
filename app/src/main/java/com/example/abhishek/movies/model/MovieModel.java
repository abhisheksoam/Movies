package com.example.abhishek.movies.model;

import android.support.v4.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abhishek on 16/07/17.
 */

public class MovieModel implements Serializable {


    private Integer voteCount;
    private Integer id;
    private Integer voteAverage;
    private String title;
    private Integer popularity;
    private String posterPath;
    private String originalLanguage;
    private String originalTitle;
    public ArrayList<Integer> genreIds;
    private String backdropPath;
    private String overview;
    private String releaseDate;
    private String adult;
    private String video;

    // Extra Field when specifically fetched a movie id Detail
    /**
     *  One async task
     */
    private String imdbId;
    private Integer revenue;
    private Integer runtime;
    private Integer budget;
    private ArrayList<Pair<String,String>> productionCountries;
    private ArrayList<Pair<String,Integer>> productionCompanies;
    private ArrayList<Pair<String,String>> spokenLanguages;
    private ArrayList<Pair<String,Integer>> genre;
    private ArrayList<Pair<String,Integer>> keywords;

    // Models
    /**
     *  Will require 5 Different Threads for fetching
     */
    private CastModels movieCast;
    private MovieImages movieImages;
    private ArrayList<Trailer> movieTrailer;
    private ArrayList<Integer> recommendationMovies;
    private ArrayList<Integer> similarMovies;



    public MovieModel(){
        genreIds =  new ArrayList<>();
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Integer voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



}
