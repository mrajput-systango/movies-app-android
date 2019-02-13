package com.otb.mvvmboilerplate.di.component;

import com.otb.mvvmboilerplate.activity.MovieListActivity;
import com.otb.mvvmboilerplate.di.module.AppModule;
import com.otb.mvvmboilerplate.di.module.MovieModule;
import com.otb.mvvmboilerplate.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohit Rajput on 12/2/19.
 */

@Component(modules = {AppModule.class, NetworkModule.class, MovieModule.class})
@Singleton
public interface AppComponent {

    void inject(MovieListActivity movieListActivity);

}
