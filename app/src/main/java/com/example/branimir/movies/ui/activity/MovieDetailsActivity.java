package com.example.branimir.movies.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.branimir.movies.BuildConfig;
import com.example.branimir.movies.MoviesApplication;
import com.example.branimir.movies.R;
import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.ui.adapter.GenreAdapter;
import com.example.branimir.movies.usecase.MovieDetailsUseCase;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE_ID = "movieId";

    @Inject
    MovieDetailsUseCase movieDetailsUseCase;

    private CompositeDisposable compositeDisposable;

    private int movieId;

    private RoundedBitmapDrawable roundedBitmapDrawable;

    @BindView(R.id.imv_poster)
    ImageView mImvPoster;
    @BindView(R.id.txv_title)
    TextView mTxvMovieTitle;
    @BindView(R.id.imv_favourite)
    ImageView mImvFavourite;
    @BindView(R.id.txv_ratings)
    TextView mTxvRating;
    @BindView(R.id.txv_release_date)
    TextView mTxvReleaseDate;
    @BindView(R.id.txv_plot_details)
    TextView mTxvPlotDetails;
    @BindView(R.id.rv_genres)
    RecyclerView mRvGenres;
    @BindView(android.R.id.content)
    View snackBarView;
    @BindView(R.id.progress_rating)
    ProgressBar mProgressRating;

    private GenreAdapter genreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);

        ((MoviesApplication) getApplication()).getDependencyInjectionComponent().inject(this);

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(movieDetailsUseCase.getMovieDetails(movieId).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setMovieDetails, Throwable::printStackTrace));

        genreAdapter = new GenreAdapter(this);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        flowLayoutManager.setAutoMeasureEnabled(true);
        mRvGenres.setLayoutManager(flowLayoutManager);
        mRvGenres.setAdapter(genreAdapter);

        loadPlaceholder();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    private void setMovieDetails(Movie movie) {
        Glide.with(this)
                .load(BuildConfig.IMG_PATH + movie.getPosterPath())
                .apply(new RequestOptions()
                        .placeholder(roundedBitmapDrawable)
                        .error(roundedBitmapDrawable)
                        .dontAnimate()
                        .dontTransform())
                .into(mImvPoster);

        mTxvMovieTitle.setText(movie.getTitle());
        mTxvRating.setText(String.valueOf(movie.getRating()));
        mProgressRating.setProgress((int) (movie.getRating() * 10));
        mTxvPlotDetails.setText(movie.getOverview());
        mTxvReleaseDate.setText(movie.getReleaseDate());

        genreAdapter.addGenres(movie.getGenres());
    }

    private void loadPlaceholder() {
        Bitmap placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.movie_placeholder);
        roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), placeholder);
        roundedBitmapDrawable.setCornerRadius(25F);
    }
}
