package com.otb.mvvmboilerplate;

import android.app.Application;
import android.content.Context;

import com.otb.mvvmboilerplate.di.component.AppComponent;
import com.otb.mvvmboilerplate.di.component.DaggerAppComponent;
import com.otb.mvvmboilerplate.di.module.AppModule;
import com.otb.mvvmboilerplate.di.module.MovieModule;
import com.otb.mvvmboilerplate.di.module.NetworkModule;

/**
 * Created by Mohit Rajput on 12/2/19.
 * TODO: Insert javadoc information here
 */
public class MoviesApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .movieModule(new MovieModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
