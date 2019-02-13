package com.otb.mvvmboilerplate.di.module;

import android.arch.lifecycle.ViewModelProvider;

import com.otb.mvvmboilerplate.data.MovieRepository;
import com.otb.mvvmboilerplate.network.ApiCallInterface;
import com.otb.mvvmboilerplate.utils.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohit Rajput on 13/2/19.
 */
@Module
public class MovieModule {
    @Provides
    @Singleton
    MovieRepository getMovieRepository(ApiCallInterface apiCallInterface) {
        return new MovieRepository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(MovieRepository movieRepository) {
        return new ViewModelFactory(movieRepository);
    }
}
