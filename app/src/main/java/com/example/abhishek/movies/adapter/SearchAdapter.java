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
import com.example.abhishek.movies._interface.onRecyclerItemClick;
import com.example.abhishek.movies.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abhishek on 31/07/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ItemViewHolder>  {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<MovieModel> list;



    private onRecyclerItemClick onRecyclerItemClick;

    public SearchAdapter(Context context){
        this.context = context;
        inflater =  LayoutInflater.from(context);
    }
    @Override
    public SearchAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_screen_row_item,null,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ItemViewHolder holder, int position) {
        MovieModel model = list.get(position);
        String backgroundPath = model.getBackdropPath();
        String url = "http://image.tmdb.org/t/p/w300" + backgroundPath;
        Picasso.with(context).load(url).into(holder.movieThumbnail);
        holder.movieName.setText(model.getOriginalTitle());
        holder.movieYear.setText(model.getReleaseDate());
        holder.movieDescription.setText(model.getOverview());
        holder.movieLanguage.setText("Language -"+ model.getOriginalLanguage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setOnRecyclerItemClick(com.example.abhishek.movies._interface.onRecyclerItemClick onRecyclerItemClick) {
        this.onRecyclerItemClick = onRecyclerItemClick;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView movieThumbnail;
        TextView movieName;
        TextView movieLanguage;
        TextView movieYear;
        TextView movieDescription;
        public ItemViewHolder(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.search_movie_image);
            movieName = (TextView) itemView.findViewById(R.id.search_movie_name);
            movieLanguage = (TextView) itemView.findViewById(R.id.search_movie_language);
            movieYear = (TextView) itemView.findViewById(R.id.search_movie_year);
            movieDescription = (TextView) itemView.findViewById(R.id.search_movie_synopsis);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onRecyclerItemClick!=null){
                        onRecyclerItemClick.onClick(v,getAdapterPosition());
                    }
                }
            });

        }
    }
}
