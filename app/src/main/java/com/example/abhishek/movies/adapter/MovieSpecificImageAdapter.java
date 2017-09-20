package com.example.abhishek.movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.abhishek.movies.R;
import com.example.abhishek.movies.model.MovieImage;
import com.example.abhishek.movies.model.MovieModel;
import com.example.abhishek.movies.model.MovieModels;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abhishek on 20/09/17.
 */

public class MovieSpecificImageAdapter extends RecyclerView.Adapter<MovieSpecificImageAdapter.ItemViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private MovieModel movie;
    private int dataSize;
    private ArrayList<MovieImage> list;
    private static final String TAG = "IMAGE ADAPTER";

    public MovieSpecificImageAdapter(Context context, MovieModel movieModels){
        this.movie = movieModels;
        layoutInflater =  LayoutInflater.from(context);
        this.context = context;
        dataSize = movie.getMovieImages().getBackdropPaths().size() + movie.getMovieImages().getPosterPath().size();
        if(dataSize>20){
            dataSize = 20;
        }
        list = new ArrayList<>();
        list.addAll(movie.getMovieImages().getBackdropPaths());
        list.addAll(movie.getMovieImages().getPosterPath());
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.content_movie_common_recycler_item_layout,null,false);
        int height = parent.getMeasuredHeight() / 4;
        int width = parent.getMeasuredWidth() / 4;
        view.setMinimumHeight(height);
        view.setMinimumWidth(width);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if(position>19){
            return;
        }else{
            MovieImage model = list.get(position);
            String url = "http://image.tmdb.org/t/p/w300" + model.getFilePath();
            Log.e(TAG,url);
            Picasso.with(context).load(url).into(holder.movieImageView);
        }
    }

    @Override
    public int getItemCount() {
        return dataSize;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImageView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            movieImageView = (ImageView) itemView.findViewById(R.id.upcoming_movie_image);
        }
    }
}
