package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.cache.IGithubReposCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepos;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RetrofitGithubRepos implements IGithubRepos {
    IDataSource api;
    final INetworkStatus networkStatus;
    private final IGithubReposCache cache;

    public PublishSubject<List<GithubRepo>> subject = PublishSubject.create();

    public RetrofitGithubRepos(IDataSource api, INetworkStatus status, IGithubReposCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
        cache.subscribe(subject);
    }

    @Override
    public Single<List<GithubRepo>> getRepos(GithubUser githubUser) {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.getRepos(githubUser.getReposUrl()).flatMap((repos) -> {
                    subject.onNext(repos);
                    return Single.fromCallable(() -> repos);
                });

            } else {
                return cache.getRepos(githubUser);
            }
        }).subscribeOn(Schedulers.io());
        //return api.getRepos(url).subscribeOn(Schedulers.io());
    }
}
