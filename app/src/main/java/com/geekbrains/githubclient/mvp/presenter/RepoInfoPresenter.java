package com.geekbrains.githubclient.mvp.presenter;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.view.RepoInfoView;
import com.geekbrains.githubclient.navigation.Screens;

import javax.inject.Inject;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class RepoInfoPresenter extends MvpPresenter<RepoInfoView> {

    private GithubRepo githubRepo;

    @Inject
    Router router;

    public RepoInfoPresenter(GithubRepo githubRepo) {
        this.githubRepo = githubRepo;
        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    public boolean backPressed() {
        router.backTo(new Screens.UsersScreen());
        return true;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init(githubRepo);
    }
}
