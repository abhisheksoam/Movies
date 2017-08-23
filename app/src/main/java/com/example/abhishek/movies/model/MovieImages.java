package com.example.abhishek.movies.model;

import java.util.ArrayList;

/**
 * Created by abhishek on 13/08/17.
 */

public class MovieImages {
    private ArrayList<MovieImage> posterPath;
    private ArrayList<MovieImage> backdropPaths;
    private Integer movieId;

    public MovieImages(){
        posterPath = new ArrayList<>();
        backdropPaths = new ArrayList<>();
    }

    public ArrayList<MovieImage> getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(ArrayList<MovieImage> posterPath) {
        this.posterPath = posterPath;
    }

    public ArrayList<MovieImage> getBackdropPaths() {
        return backdropPaths;
    }

    public void setBackdropPaths(ArrayList<MovieImage> backdropPaths) {
        this.backdropPaths = backdropPaths;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
