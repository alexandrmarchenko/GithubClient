package com.geekbrains.githubclient.mvp.presenter;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.view.RepoInfoView;
import com.geekbrains.githubclient.navigation.Screens;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class RepoInfoPresenter extends MvpPresenter<RepoInfoView> {

    private final Router mRouter;
    private GithubRepo mGithubRepo;

    public RepoInfoPresenter(GithubRepo githubRepo, Router router) {
        mGithubRepo = githubRepo;
        mRouter = router;
    }

    public boolean backPressed() {
        mRouter.backTo(new Screens.UsersScreen());
        return true;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init(mGithubRepo);
    }
}
