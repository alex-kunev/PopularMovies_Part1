package com.example.popularmovies_part1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Movie [] mMovieData;
    private final OnMovieListener mOnMovieListener;

    public MovieAdapter(Movie[] mMovieData, OnMovieListener onMovieListener){
        this.mMovieData = mMovieData;
        this.mOnMovieListener = onMovieListener;
    }

    public void setmMovieData(Movie[] mMovieData) {
        this.mMovieData = mMovieData;
        notifyDataSetChanged();
    }

    public interface OnMovieListener {
        void onClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView mMovieImageView;
        OnMovieListener onMovieListener;


        public MyViewHolder (View view, OnMovieListener onMovieListener) {
            super(view);
            mMovieImageView = view.findViewById(R.id.iv_movie_posters);
            this.onMovieListener = onMovieListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnMovieListener.onClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int movieListLayout = R.layout.movies_list_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(movieListLayout, parent, false);
        return new MyViewHolder(itemView, mOnMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MyViewHolder holder, int i) {
        String movieToBind = mMovieData[i].getPoster();
        Picasso.get()
                .load(movieToBind)
                .into(holder.mMovieImageView);
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) {
            return 0;
        }
        return mMovieData.length;
    }
}
