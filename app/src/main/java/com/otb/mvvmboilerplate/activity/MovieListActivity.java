package com.otb.mvvmboilerplate.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.otb.mvvmboilerplate.MoviesApplication;
import com.otb.mvvmboilerplate.R;
import com.otb.mvvmboilerplate.adapter.MovieAdapter;
import com.otb.mvvmboilerplate.model.Movie;
import com.otb.mvvmboilerplate.network.response.ApiResponse;
import com.otb.mvvmboilerplate.network.response.GetMoviesResponse;
import com.otb.mvvmboilerplate.utils.NetworkUtils;
import com.otb.mvvmboilerplate.utils.ViewModelFactory;
import com.otb.mvvmboilerplate.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends BaseActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    private List<Movie> listMovies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ((MoviesApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        initView();
        init();
    }

    private void initView() {
        listMovies = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, listMovies);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {
        MoviesViewModel moviesViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel.class);
        moviesViewModel.observeMoviesResponse().observe(this, this::consumeResponse);
        if (NetworkUtils.isNetworkAvailable(this)) {
            moviesViewModel.fetchMovies();
        } else {
            Toast.makeText(this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {

            case LOADING:
                showLoading(R.string.loading_movies);
                break;

            case SUCCESS:
                dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                dismiss();
                Toast.makeText(this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    private void renderSuccessResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            GetMoviesResponse moviesResponse = new Gson().fromJson(response.toString(), GetMoviesResponse.class);
            List<Movie> moviesList = moviesResponse.getData();
            if (moviesList != null) {
                listMovies.clear();
                listMovies.addAll(moviesList);
                movieAdapter.notifyDataSetChanged();
            }
        } else {
            Toast.makeText(this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }
}
