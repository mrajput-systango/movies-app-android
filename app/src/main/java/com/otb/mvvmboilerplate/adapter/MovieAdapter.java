package com.otb.mvvmboilerplate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.otb.mvvmboilerplate.R;
import com.otb.mvvmboilerplate.activity.MovieDetailsActivity;
import com.otb.mvvmboilerplate.model.Movie;
import com.otb.mvvmboilerplate.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohit Rajput on 12/2/19.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> listMovies;

    public MovieAdapter(Context context, List<Movie> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int position) {
        Movie movie = listMovies.get(position);
        if (movie != null) {
            if (!TextUtils.isEmpty(movie.getPoster())) {
                GlideUtils.loadImage(context, movie.getPoster(), viewHolder.ivPoster, 0);
            }
            if (!TextUtils.isEmpty(movie.getTitle())) {
                viewHolder.tvTitle.setText(movie.getTitle());
            }
            viewHolder.itemView.setOnClickListener(v -> {
                MovieDetailsActivity.start(context, movie);
            });
        }
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPoster)
        ImageView ivPoster;
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
