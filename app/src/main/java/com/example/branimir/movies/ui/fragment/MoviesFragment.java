package com.example.branimir.movies.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.branimir.movies.R;
import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.ui.activity.MovieDetailsActivity;
import com.example.branimir.movies.ui.adapter.MoviesAdapter;
import com.example.branimir.movies.ui.listener.MovieItemClickListener;
import com.example.branimir.movies.ui.utils.GridSpacingItemDecoration;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;


public abstract class MoviesFragment extends Fragment implements MovieItemClickListener {

    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MoviesAdapter mAdapter;

    private List<Movie> movies;

    private CompositeDisposable compositeDisposable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);

        injectDependencies();

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(getMovies().observeOn(AndroidSchedulers.mainThread()).subscribe(movies -> {
            mAdapter.setMovies(movies);
            mAdapter.notifyDataSetChanged();
        }, Throwable::printStackTrace));

        mAdapter = new MoviesAdapter(getActivity(), this);
        RecyclerView.LayoutManager mMoviesLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvMovies.setLayoutManager(mMoviesLayoutManager);
        rvMovies.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(), true));
        rvMovies.setAdapter(mAdapter);
        rvMovies.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


    @Override
    public void onMovieItemClick(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movie.getId());
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.dispose();
    }

    private int dpToPx() {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics()));
    }

    protected abstract Single<List<Movie>> getMovies();
    protected abstract void injectDependencies();

}
