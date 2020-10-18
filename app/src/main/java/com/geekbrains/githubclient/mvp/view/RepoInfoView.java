package com.geekbrains.githubclient.mvp.view;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface RepoInfoView extends MvpView {
    void init(GithubRepo githubRepo);
}
