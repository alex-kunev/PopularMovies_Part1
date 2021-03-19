package com.example.popularmovies_part1;

public class Movie {
    private String title;
    private String poster;
    private String release;
    private String rating;
    private String overview;

    public Movie(String title, String poster, String release, String rating, String overview){
        this.title = title;
        this.poster = poster;
        this.release = release;
        this.rating = rating;
        this.overview = overview;
    }

    public Movie(){
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
