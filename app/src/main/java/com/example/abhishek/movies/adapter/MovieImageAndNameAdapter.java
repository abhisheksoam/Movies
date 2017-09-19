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

/**
 * Created by abhishek on 19/09/17.
 */

public class MovieImageAndNameAdapter extends RecyclerView.Adapter<MovieImageAndNameAdapter.ItemViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private MovieModels movieModels;
    private String TAG = "MOVIE ADAPTER";

    public MovieImageAndNameAdapter(Context context, MovieModels movieModels){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.movieModels = movieModels;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_image_name_row_item,null,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        MovieModel model =  movieModels.list.get(position);
        String url = "http://image.tmdb.org/t/p/w300" + model.getBackdropPath();
        Log.e(TAG,url);
        Picasso.with(context).load(url).into(holder.movieImage);
        holder.movieName.setText(model.getOriginalTitle());
    }

    @Override
    public int getItemCount() {
        return movieModels.list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;
        TextView movieName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieName = (TextView) itemView.findViewById(R.id.movie_name);
        }
    }

}
