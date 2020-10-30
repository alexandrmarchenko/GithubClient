package com.geekbrains.githubclient;

import android.app.Application;
import android.content.Context;

import com.geekbrains.githubclient.di.AppComponent;
import com.geekbrains.githubclient.di.DaggerAppComponent;
import com.geekbrains.githubclient.di.module.AppModule;

public class GithubApplication extends Application {
    public static final boolean DEBUG = true;
    public static GithubApplication INSTANCE;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static Context getAppContext() {
        return INSTANCE;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
