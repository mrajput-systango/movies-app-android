package com.otb.mvvmboilerplate.network;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.otb.mvvmboilerplate.network.ApiUrls.GET_MOVIES_LIST;

/**
 * Created by Mohit Rajput on 12/2/19.
 * Retrofit API calling interface
 */
public interface ApiCallInterface {
    @GET(GET_MOVIES_LIST)
    Observable<JsonElement> getMovies();
}
