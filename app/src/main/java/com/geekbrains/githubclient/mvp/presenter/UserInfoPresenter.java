package com.geekbrains.githubclient.mvp.presenter;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.view.UserInfoView;
import com.geekbrains.githubclient.navigation.Screens;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UserInfoPresenter extends MvpPresenter<UserInfoView> {

    private Router mRouter = GithubApplication.INSTANCE.getRouter();

    private GithubUser mGithubUser;

    public UserInfoPresenter(GithubUser githubUser) {
        mGithubUser = githubUser;
    }

    public boolean backPressed() {
        mRouter.backTo(new Screens.UsersScreen());
        return true;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init(mGithubUser);
    }
}
