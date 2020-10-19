package com.geekbrains.githubclient.mvp.model.cache;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface IGithubReposCache {
    Single<List<GithubRepo>> getRepos(GithubUser githubUser);

    void insertUsers(List<GithubRepo> githubRepos);

    void subscribe(Observable<List<GithubRepo>> obs);
}
