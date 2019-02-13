package com.otb.mvvmboilerplate.network;


import static com.otb.mvvmboilerplate.BuildConfig.SERVER_BASE_URL;

/**
 * Created by Mohit Rajput on 11/2/19.
 * Contains all REST API URLs
 * BASE_URL comes from Build Config which is different in each environment.
 */

public interface ApiUrls {
    //Server APIs
    String GET_MOVIES_LIST = SERVER_BASE_URL + "/ios-movies/list.json";
}
