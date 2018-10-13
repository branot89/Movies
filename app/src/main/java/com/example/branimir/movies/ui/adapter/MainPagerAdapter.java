package com.example.branimir.movies.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.branimir.movies.R;
import com.example.branimir.movies.ui.fragment.FavoriteMoviesFragment;
import com.example.branimir.movies.ui.fragment.PopularMoviesFragment;
import com.example.branimir.movies.ui.fragment.TopRatedMoviesFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private TopRatedMoviesFragment topRated;
    private PopularMoviesFragment popular;
    private FavoriteMoviesFragment favorite;

    private Context context;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        topRated = TopRatedMoviesFragment.newInstance();
        popular = PopularMoviesFragment.newInstance();
        favorite = FavoriteMoviesFragment.newInstance();
        this.context = context;
    }



    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return topRated;
            case 1:
                return popular;
            case 2:
                return favorite;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return context.getString(R.string.top_rated);
            case 1: return context.getString(R.string.popular);
            case 2: return context.getString(R.string.favorite);
        }
        return null;
    }
}
