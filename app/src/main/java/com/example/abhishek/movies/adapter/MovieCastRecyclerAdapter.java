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
import com.example.abhishek.movies.model.CastModel;
import com.example.abhishek.movies.model.CastModels;
import com.squareup.picasso.Picasso;

/**
 * Created by abhishek on 29/08/17.
 */

public class MovieCastRecyclerAdapter extends RecyclerView.Adapter<MovieCastRecyclerAdapter.ItemViewHolder> {

    private static final String TAG = "CAST_ADAPTER";

    private Context context;
    private CastModels castModels;
    private LayoutInflater inflater;

    public MovieCastRecyclerAdapter(Context context,CastModels castModels){
        Log.e(TAG,"In constructor");
        Log.e(TAG,"Size of list "+ castModels.getCastSize());
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.castModels = castModels;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cast_row_item_layout,null,false);
        int height = parent.getMeasuredHeight() / 4;
        int width = parent.getMeasuredWidth() / 4;
        view.setMinimumHeight(height);
        view.setMinimumWidth(width);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CastModel model =  castModels.getCastModel(position);
        String url = "http://image.tmdb.org/t/p/w300" + model.getProfilePath();
        Log.e(TAG,url);
        Picasso.with(context).load(url).into(holder.castImage);
        holder.castName.setText(model.getCharacterName());
    }

    @Override
    public int getItemCount() {
        return castModels.getCastSize();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView castImage;
        TextView castName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            castImage = (ImageView) itemView.findViewById(R.id.cast_image);
            castName = (TextView) itemView.findViewById(R.id.cast_name);
        }
    }
}
