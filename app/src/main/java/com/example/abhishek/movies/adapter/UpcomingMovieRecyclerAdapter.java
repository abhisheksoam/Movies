package com.example.abhishek.movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.abhishek.movies.R;

import com.example.abhishek.movies.activity.MovieScreen;
import com.example.abhishek.movies.activity.SearchActivity;
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
        Log.e(TAG,"Movie Type: "+ movieModel.getType());
        Log.e(TAG,"Movie List Size: "+ movieModel.list.size());
        this.movies = movieModel;
        layoutInflater =  LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = layoutInflater.inflate(R.layout.content_movie_common_recycler_item_layout,null,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MovieModel model = movies.list.get(position);

        String movieName;
        String posterPath;
        String backgroundPath;

        movieName = model.getOriginalTitle();
        posterPath = model.getPosterPath();
        backgroundPath = model.getBackdropPath();
//        holder.movieNameTextView.setText(movieName);
        String url = "http://image.tmdb.org/t/p/w300" + backgroundPath;
        Picasso.with(context).load(url).into(holder.movieImageView);

    }

    @Override
    public int getItemCount() {
        return movies.list.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImageView;
//        TextView movieNameTextView;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            movieImageView = (ImageView) itemView.findViewById(R.id.upcoming_movie_image);
            movieImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"On clicked");
                    Intent intent = new Intent(context, MovieScreen.class);
                    intent.putExtra("MovieDetail",movies.list.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });


        }
    }
}
