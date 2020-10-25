package com.geekbrains.githubclient.di;

import com.geekbrains.githubclient.di.module.ApiModule;
import com.geekbrains.githubclient.di.module.AppModule;
import com.geekbrains.githubclient.di.module.CacheModule;
import com.geekbrains.githubclient.di.module.CiceroneModule;
import com.geekbrains.githubclient.di.module.RepoModule;
import com.geekbrains.githubclient.mvp.presenter.MainPresenter;
import com.geekbrains.githubclient.mvp.presenter.RepoInfoPresenter;
import com.geekbrains.githubclient.mvp.presenter.UserInfoPresenter;
import com.geekbrains.githubclient.mvp.presenter.UsersPresenter;
import com.geekbrains.githubclient.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
                RepoModule.class
        }
)
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(UsersPresenter usersPresenter);

    void inject(UserInfoPresenter userInfoPresenter);

    void inject(RepoInfoPresenter repoInfoPresenter);

}