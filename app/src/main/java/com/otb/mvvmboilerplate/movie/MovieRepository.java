package com.otb.mvvmboilerplate.movie;

import com.google.gson.JsonElement;
import com.otb.mvvmboilerplate.network.ApiCallInterface;

import io.reactivex.Observable;

/**
 * Created by Mohit Rajput on 11/2/19.
 * Movie list data provider
 */
public class MovieRepository {
    private ApiCallInterface apiCallInterface;

    public MovieRepository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    public Observable<JsonElement> fetchAllMovies() {
        return apiCallInterface.getMovies();
    }
}
