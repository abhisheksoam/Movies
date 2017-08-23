package com.example.abhishek.movies.model;

import java.util.ArrayList;

/**
 * Created by abhishek on 13/08/17.
 */

public class MovieImage {
    private Integer aspectRatio;
    private String filePath;
    private Integer height;
    private Integer width;

    public Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }


}

