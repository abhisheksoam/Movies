package com.example.abhishek.movies.model;

import java.util.ArrayList;

/**
 * Created by abhishek on 13/08/17.
 */

public class CastModels {
    private Integer movieId;
    private ArrayList<CastModel> cast;

    public CastModels(){
        cast = new ArrayList<>();
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public ArrayList<CastModel> getCast() {
        return cast;
    }

    public void setCast(ArrayList<CastModel> cast) {
        this.cast = cast;
    }

    public void addCast(CastModel castModel){
        cast.add(castModel);
    }

    public CastModel getCastModel(int position){
        return cast.get(position);
    }

    public int getCastSize(){
        return cast.size();
    }

}
