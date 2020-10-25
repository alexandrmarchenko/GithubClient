package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.cache.IGithubReposCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitGithubRepositoriesRepo implements IGithubRepositoriesRepo {
    IDataSource api;
    final INetworkStatus networkStatus;
    private final IGithubReposCache cache;


    public RetrofitGithubRepositoriesRepo(IDataSource api, INetworkStatus status, IGithubReposCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
    }

    @Override
    public Single<List<GithubRepo>> getRepos(GithubUser githubUser) {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.getRepos(githubUser.getReposUrl()).flatMap((repos) -> {
                    return cache.insertUsers(repos).toSingleDefault(repos);
                });

            } else {
                return cache.getRepos(githubUser);
            }
        }).subscribeOn(Schedulers.io());
    }
}
