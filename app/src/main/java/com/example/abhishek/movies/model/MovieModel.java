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

    // Extra Field

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

    private CastModels movieCast;
    private MovieImages movieImages;
    private ArrayList<Trailer> movieTrailer;
    private MovieModels recommendationMovies;
    private MovieModels similarMovies;

    public MovieModels getRecommendationMovies() {
        return recommendationMovies;
    }

    public void setRecommendationMovies(MovieModels recommendationMovies) {
        this.recommendationMovies = recommendationMovies;
    }

    public MovieModels getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(MovieModels similarMovies) {
        this.similarMovies = similarMovies;
    }

    public MovieModel(){
        genreIds =  new ArrayList<>();
        productionCompanies = new ArrayList<>();
        productionCountries = new ArrayList<>();
        spokenLanguages = new ArrayList<>();
        genre = new ArrayList<>();
        keywords = new ArrayList<>();
        movieTrailer = new ArrayList<>();
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


    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(ArrayList<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public ArrayList<Pair<String, String>> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(ArrayList<Pair<String, String>> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public ArrayList<Pair<String, Integer>> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<Pair<String, Integer>> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public ArrayList<Pair<String, String>> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(ArrayList<Pair<String, String>> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public ArrayList<Pair<String, Integer>> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<Pair<String, Integer>> genre) {
        this.genre = genre;
    }

    public ArrayList<Pair<String, Integer>> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Pair<String, Integer>> keywords) {
        this.keywords = keywords;
    }

    public CastModels getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(CastModels movieCast) {
        this.movieCast = movieCast;
    }

    public MovieImages getMovieImages() {
        return movieImages;
    }

    public void setMovieImages(MovieImages movieImages) {
        this.movieImages = movieImages;
    }

    public ArrayList<Trailer> getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(ArrayList<Trailer> movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

}
