package com.otb.mvvmboilerplate.movie;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.otb.mvvmboilerplate.data.MovieRepository;
import com.otb.mvvmboilerplate.network.ApiCallInterface;
import com.otb.mvvmboilerplate.network.Status;
import com.otb.mvvmboilerplate.network.response.ApiResponse;
import com.otb.mvvmboilerplate.network.response.GetMoviesResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.Mockito.when;

/**
 * Created by Mohit Rajput on 13/2/19.
 * TODO: Insert javadoc information here
 */

@RunWith(MockitoJUnitRunner.class)
public class MovieTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    private MovieRepository movieRepository;
    @Mock
    private ApiCallInterface apiCallInterface;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieRepository = new MovieRepository(apiCallInterface);
    }

    @Test
    public void testFetchAllMovies_emitCorrectData() {
        JsonElement jsonElement = new JsonArray();
        when(movieRepository.fetchAllMovies()).thenReturn(Observable.just(jsonElement));
    }

    @Test
    public void testFetchAllMovies_testLoadingStarted() {
        MutableLiveData<ApiResponse> responseMutableLiveData = new MutableLiveData<>();
        ApiResponse apiResponse = ApiResponse.loading();
        responseMutableLiveData.postValue(apiResponse);
        Assert.assertEquals(Status.LOADING, responseMutableLiveData.getValue().status);
    }

    @Test
    public void testFetchAllMovies_testSuccessIfJsonIsNotNull() {
        MutableLiveData<ApiResponse> responseMutableLiveData = new MutableLiveData<>();
        JsonElement jsonElement = new JsonArray();
        ApiResponse apiResponse = ApiResponse.success(jsonElement);
        responseMutableLiveData.postValue(apiResponse);
        Assert.assertEquals(Status.SUCCESS, responseMutableLiveData.getValue().status);
        Assert.assertNotNull(responseMutableLiveData.getValue().data);
        Assert.assertNull(responseMutableLiveData.getValue().error);
    }

    @Test
    public void testFetchAllMovies_testSuccessIsProperJson() {
        MutableLiveData<ApiResponse> responseMutableLiveData = new MutableLiveData<>();
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("data", jsonArray);

        GetMoviesResponse moviesResponse = new Gson().fromJson(jsonObject.toString(), GetMoviesResponse.class);

        ApiResponse apiResponse = ApiResponse.success(jsonObject);
        responseMutableLiveData.postValue(apiResponse);
        Assert.assertEquals(Status.SUCCESS, responseMutableLiveData.getValue().status);
        Assert.assertNotNull(moviesResponse);
        Assert.assertNull(responseMutableLiveData.getValue().error);
    }

    @Test
    public void testFetchAllMovies_testError() {
        MutableLiveData<ApiResponse> responseMutableLiveData = new MutableLiveData<>();
        Exception exception = new Exception();
        ApiResponse apiResponse = ApiResponse.error(exception);
        responseMutableLiveData.postValue(apiResponse);
        Assert.assertEquals(Status.ERROR, responseMutableLiveData.getValue().status);
        Assert.assertNull(responseMutableLiveData.getValue().data);
        Assert.assertNotNull(responseMutableLiveData.getValue().error);
    }
}
