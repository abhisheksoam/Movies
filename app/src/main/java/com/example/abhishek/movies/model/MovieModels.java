package com.example.abhishek.movies.model;

import java.util.ArrayList;

/**
 * Created by abhishek on 16/07/17.
 */

public class MovieModels {



    private String type;
    public ArrayList<MovieModel> list;
    private Integer currentPage;
    private Integer totalPage;
    private Integer totalResults;


    public MovieModels(String type){
        this.type = type;
        list = new ArrayList<>();
    }


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
