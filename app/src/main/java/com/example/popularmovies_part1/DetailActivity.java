    package com.example.popularmovies_part1;

    import android.content.Intent;
    import android.os.Bundle;
    import androidx.appcompat.app.AppCompatActivity;
    import android.widget.ImageView;
    import android.widget.TextView;
    import com.squareup.picasso.Picasso;


    public class DetailActivity extends AppCompatActivity {

        ImageView mMoviePosterDisplay;
        TextView mMovieTitleDisplay;
        TextView mMovieRateDisplay;
        TextView mMovieReleaseDisplay;
        TextView mMoviePlotSynopsisDisplay;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            mMoviePosterDisplay = findViewById(R.id.iv_detail_movie_poster);
            mMovieTitleDisplay = findViewById(R.id.tv_detail_title);
            mMovieRateDisplay = findViewById(R.id.tv_detail_rate);
            mMovieReleaseDisplay = findViewById(R.id.tv_detail_release_date);
            mMoviePlotSynopsisDisplay = findViewById(R.id.tv_overview);


            Intent intentActivity = getIntent();

            if (intentActivity != null) {
                if (intentActivity.hasExtra(Intent.EXTRA_TEXT)) {
                    String poster = intentActivity.getStringExtra("poster");
                    String title = intentActivity.getStringExtra("title");
                    String rate = intentActivity.getStringExtra("rate");
                    String release = intentActivity.getStringExtra("release");
                    String overview = intentActivity.getStringExtra("overview");

                    mMovieTitleDisplay.setText(title);
                    mMoviePlotSynopsisDisplay.setText(overview);
                    mMovieRateDisplay.setText(rate);
                    mMovieReleaseDisplay.setText(release);
                    Picasso.get()
                            .load(poster)
                            .into(mMoviePosterDisplay);
                }
            }
        }
    }
