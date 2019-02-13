package com.otb.mvvmboilerplate.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.otb.mvvmboilerplate.movie.MovieRepository;
import com.otb.mvvmboilerplate.network.response.ApiResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mohit Rajput on 11/2/19.
 * Movies data view-model
 */
public class MoviesViewModel extends ViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();
    private MovieRepository movieRepository;
    private MutableLiveData<ApiResponse> moviesLiveData = new MutableLiveData<>();

    @Inject
    public MoviesViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MutableLiveData<ApiResponse> observeMoviesResponse() {
        return moviesLiveData;
    }

    public void fetchMovies() {
        disposables.add(movieRepository.fetchAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> moviesLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> moviesLiveData.setValue(ApiResponse.success(result)),
                        throwable -> moviesLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
