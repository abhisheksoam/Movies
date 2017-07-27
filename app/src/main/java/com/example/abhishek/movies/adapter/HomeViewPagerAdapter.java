package com.example.abhishek.movies.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.movies.R;
import com.example.abhishek.movies._interface.CustomAsyncInterface;
import com.example.abhishek.movies.appDetails.Constants;
import com.example.abhishek.movies.asyncTask.CustomAsyncTask;
import com.example.abhishek.movies.fragment.NowPlayingMovieFragment;
import com.example.abhishek.movies.fragment.PopularMovieFragment;
import com.example.abhishek.movies.fragment.UpcomingMovieFragment;
import com.example.abhishek.movies.model.MovieModels;
import com.example.abhishek.movies.utility.HorizontalItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by abhishek on 06/11/16.
 */

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "HOME_VIEW_PAGER_ADAPTER";

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PopularMovieFragment();
            case 1:
                    return new UpcomingMovieFragment();
            case 2:
                return new NowPlayingMovieFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        switch (position){
            case 0:
                return "POPULAR";
            case 1:
                return "UPCOMING";
            case 2:
                return "NOW PLAYING";

            default:
                return null;
        }
    }

}
