package com.example.abhishek.movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishek.movies.R;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abhishek on 06/11/16.
 */

public class UpcomingMovieRecyclerAdapter extends RecyclerView.Adapter<UpcomingMovieRecyclerAdapter.ItemViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private MovieModels movies;

    private String TAG = "UPCOMING_ADAPTER";

    public UpcomingMovieRecyclerAdapter(Context context, MovieModels movieModel){
        Log.e(TAG,"Inside constuctor");
        this.context = context;
        this.movies = movieModel;
        Log.e(TAG,movieModel.toString());
        layoutInflater =  LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = layoutInflater.inflate(R.layout.content_movie_common_recycler_item_layout,null,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        MovieModel model = movies.list.get(position);

        String movieName;
        String posterPath;
        String backgroundPath;

        movieName = model.getOriginalTitle();
        posterPath = model.getPosterPath();
        backgroundPath = model.getBackdropPath();
        Log.e(TAG,movieName);
        holder.movieNameTextView.setText(movieName);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + backgroundPath).into(holder.movieImageView);

    }

    @Override
    public int getItemCount() {
        return movies.list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImageView;
        TextView movieNameTextView;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            movieImageView = (ImageView) itemView.findViewById(R.id.upcoming_movie_image);
            movieNameTextView = (TextView) itemView.findViewById(R.id.upcoming_movie_name);
        }
    }
}
