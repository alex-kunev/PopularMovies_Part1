package com.example.popularmovies_part1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Utility functions to handle TheMovieDataBase (https://www.themoviedb.org) JSON data
public class TMDBJsonUtils {

    public static Movie[] getMovieInformationsFromJson(Context context, String json) throws JSONException {

        final String TMDB_BASE_URL = "https://image.tmdb.org/t/p/";
        final String TMDB_POSTER_SIZE = "w500";

        final String TMDB_RESULTS = "results";
        final String TMDB_POSTER_PATH = "poster_path";
        final String TMDB_TITLE = "title";
        final String TMDB_VOTE = "vote_average";
        final String TMDB_OVERVIEW = "overview";
        final String TMDB_RELEASE_DATE = "release_date";

        JSONObject movieJson = new JSONObject(json);

        JSONArray movieArray = movieJson.getJSONArray(TMDB_RESULTS);

        Movie[] movieResults = new Movie[movieArray.length()];


        for (int i = 0; i < movieArray.length(); i++){
            String title, poster, release, rating, overview;

            //Get the JSONObject for each of the 5 variables of the Movie class
            title = movieArray.getJSONObject(i).optString(TMDB_TITLE);
            poster = movieArray.getJSONObject(i).optString(TMDB_POSTER_PATH);
            release = movieArray.getJSONObject(i).optString(TMDB_RELEASE_DATE);
            rating = movieArray.getJSONObject(i).optString(TMDB_VOTE);
            overview = movieArray.getJSONObject(i).optString(TMDB_OVERVIEW);

            //Append the 5 variables from the getters to the Movie []

            movieResults[i] = new Movie(title, TMDB_BASE_URL.concat(TMDB_POSTER_SIZE).concat(poster), release, rating, overview);
        }

        return movieResults;
    }
}
