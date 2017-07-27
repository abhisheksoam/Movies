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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abhishek on 06/11/16.
 */

public class NowPlayingMovieRecyclerAdapter extends RecyclerView.Adapter<NowPlayingMovieRecyclerAdapter.ItemViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<JSONObject> list;

    private String TAG = "NowPlayingMovieRecyclerAdapter";

    public NowPlayingMovieRecyclerAdapter(Context context, ArrayList<JSONObject> data){
        this.context = context;
        this.list = data;
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
        JSONObject object = list.get(position);
        String movieName = null,posterPath=null,backgroundPath = null;
        try {
            movieName = object.getString("original_title");
            posterPath = object.getString("poster_path");
            backgroundPath = object.getString("backdrop_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(movieName!=null){
//            holder.movieNameTextView.setText(movieName);
        }
        if(backgroundPath!=null){
            Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + backgroundPath).into(holder.movieImageView);
        }else if(posterPath!=null){
            Picasso.with(context).load("http://image.tmdb.org/t/p/w500" + posterPath).into(holder.movieImageView);
        }else{
            Log.e("No image in","Upcoming Recycler Adpater");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView movieImageView;
        TextView movieNameTextView;
        public ItemViewHolder(View itemView)
        {
            super(itemView);
            movieImageView = (ImageView) itemView.findViewById(R.id.upcoming_movie_image);
//            movieNameTextView = (TextView) itemView.findViewById(R.id.upcoming_movie_name);
        }
    }
}
