package com.geekbrains.githubclient.mvp.view;

import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface UserInfoView extends MvpView {
    void init(GithubUser githubUser);
    void updateList();
}
