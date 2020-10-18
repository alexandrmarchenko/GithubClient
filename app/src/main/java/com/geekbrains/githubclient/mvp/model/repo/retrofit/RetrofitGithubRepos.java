package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepos;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubRepos implements IGithubRepos {
    IDataSource api;

    public RetrofitGithubRepos(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<List<GithubRepo>> getRepos(String url) {
        return api.getRepos(url).subscribeOn(Schedulers.io());
    }
}
