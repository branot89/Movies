package com.example.branimir.movies.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.branimir.movies.BuildConfig;
import com.example.branimir.movies.R;
import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.ui.listener.MovieItemClickListener;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> movies;
    private Context context;
    private final MovieItemClickListener movieItemClickListener;

    public MoviesAdapter(Context context, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        } else {
            return movies.size();
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imv_movie_poster)
        ImageView mImvMovieImage;

        @BindView(R.id.txv_movie_title)
        TextView mTxvMovieTitle;

        @BindView(R.id.txv_release_date)
        TextView mTxvReleaseDate;

        @BindView(R.id.txv_movie_rating)
        TextView mTxvRatings;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void onBind(final int position) {

            final Movie movie = movies.get(position);

            Bitmap placeholder = BitmapFactory.decodeResource(context.getResources(), R.drawable.movie_placeholder);
            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), placeholder);
            roundedBitmapDrawable.setCornerRadius(25F);

            if (movie.getPosterPath() != null) {

                Glide.with(context)
                        .load(BuildConfig.IMG_PATH + movie.getPosterPath())
                        .apply(new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .placeholder(roundedBitmapDrawable)
                                .error(roundedBitmapDrawable))
                        .into(mImvMovieImage);
            }

            if (movie.getTitle() != null) {
                mTxvMovieTitle.setText(movie.getTitle());
            }

            if (movie.getReleaseDate() != null) {
                mTxvReleaseDate.setText(movie.getReleaseDate());
            }

            if (movie.getRating() != null) {
                mTxvRatings.setText(String.valueOf(movie.getRating()));
            }
        }

        @OnClick
        void onClick(View view) {
            String transitionName = "movie_item_" + String.valueOf(getAdapterPosition());
            movieItemClickListener.onMovieItemClick(movies.get(getAdapterPosition()));
        }
    }
}
