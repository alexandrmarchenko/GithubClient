package com.geekbrains.githubclient.mvp.presenter;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.view.MainView;
import com.geekbrains.githubclient.navigation.Screens;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    public MainPresenter() {
        super();

        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.UsersScreen());
    }

    public void backClicked() {
        router.exit();
    }

}
