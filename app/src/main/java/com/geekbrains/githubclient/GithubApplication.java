package com.geekbrains.githubclient;

import android.app.Application;
import android.content.Context;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class GithubApplication extends Application {
    public static final boolean DEBUG = true;
    public static GithubApplication INSTANCE;
    private Cicerone<Router> mCicerone;

    private ApiHolder mApiHolder;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

        initCicerone();
        mApiHolder = new ApiHolder();
    }

    public static Context getAppContext() {
        return INSTANCE;
    }

    private void initCicerone() {
        mCicerone = Cicerone.create();
    }

    public NavigatorHolder getNavigatorHolder() {
        return mCicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return mCicerone.getRouter();
    }

    public IDataSource getApi() {
        return mApiHolder.getDataSource();
    }

}
