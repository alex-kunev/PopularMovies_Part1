package com.example.popularmovies_part1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnMovieListener {

    private RecyclerView movieListRecyclerView;
    private MovieAdapter mMovieAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Movie [] movieData;

    String query = "popular";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieListRecyclerView = (RecyclerView) findViewById(R.id.lv_moviesList);

//        LinearLayoutManager LayoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        movieListRecyclerView.setLayoutManager(layoutManager);

        movieListRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(movieData, this);

        movieListRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData();

    }


    @Override
    public void onClick(int position) {
        Context context = this;
        Class destinationClass = DetailActivity.class;

        Intent startDetailActivity = new Intent(context, destinationClass);
        startDetailActivity.putExtra(Intent.EXTRA_TEXT, position);
        startDetailActivity.putExtra("title", movieData[position].getTitle());
        startDetailActivity.putExtra("poster", movieData[position].getPoster());
        startDetailActivity.putExtra("rate", movieData[position].getRating());
        startDetailActivity.putExtra("release", movieData[position].getRelease());
        startDetailActivity.putExtra("overview", movieData[position].getOverview());

        startActivity(startDetailActivity);

    }

    private void loadMovieData() {
        String theMovieDbQueryType = query;
        movieListRecyclerView.setVisibility(View.VISIBLE);
        new FetchMovieTask().execute(theMovieDbQueryType);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, Movie[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Movie[] doInBackground(String... params) {
            if (params.length == 0){
                return null;
            }

            String sortBy = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl(sortBy);

            try {
                String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);

                movieData
                        = TMDBJsonUtils.getMovieInformationsFromJson(MainActivity.this, jsonMovieResponse);

                return movieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movieData) {
            if (movieData != null) {
                movieListRecyclerView.setVisibility(View.INVISIBLE);
                mMovieAdapter = new MovieAdapter(movieData,MainActivity.this);
                movieListRecyclerView.setAdapter(mMovieAdapter);
            } else {
                System.out.println("There has been an error");
            }
        }

    }
}