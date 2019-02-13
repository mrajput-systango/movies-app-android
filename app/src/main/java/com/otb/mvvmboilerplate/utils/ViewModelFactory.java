package com.otb.mvvmboilerplate.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.otb.mvvmboilerplate.movie.MovieRepository;
import com.otb.mvvmboilerplate.viewmodel.MoviesViewModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Mohit Rajput on 12/2/19.
 * View-model factory to provide correct arguments constructor of a view-model
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository movieRepository;

    @Inject
    public ViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(movieRepository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}