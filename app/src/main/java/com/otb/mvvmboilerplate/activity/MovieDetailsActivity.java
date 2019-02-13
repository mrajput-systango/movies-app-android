package com.otb.mvvmboilerplate.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.otb.mvvmboilerplate.R;
import com.otb.mvvmboilerplate.constants.AppConstants;
import com.otb.mvvmboilerplate.model.Movie;
import com.otb.mvvmboilerplate.utils.GlideUtils;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.ivPoster)
    ImageView ivPoster;

    public static void start(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(AppConstants.MOVIE, Parcels.wrap(movie));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        populateMovieDetails();
    }

    private void populateMovieDetails() {
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra(AppConstants.MOVIE));
        if (!TextUtils.isEmpty(movie.getPoster())) {
            GlideUtils.loadImage(this, movie.getPoster(), ivPoster, 0);
        }
        if (!TextUtils.isEmpty(movie.getTitle())) {
            tvTitle.setText(movie.getTitle());
        }
        if (!TextUtils.isEmpty(movie.getSummary())) {
            tvDescription.setText(movie.getSummary());
        }
    }
}
