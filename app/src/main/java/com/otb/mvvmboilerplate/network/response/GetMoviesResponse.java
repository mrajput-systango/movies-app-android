package com.otb.mvvmboilerplate.network.response;

import com.google.gson.annotations.SerializedName;
import com.otb.mvvmboilerplate.model.Movie;

import java.util.List;

/**
 * Created by Mohit Rajput on 11/2/19.
 * Movies list response
 */
public class GetMoviesResponse {

    @SerializedName("data")
    private List<Movie> data;

    public List<Movie> getData() {
        return data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }
}
