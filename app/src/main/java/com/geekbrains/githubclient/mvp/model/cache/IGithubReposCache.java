package com.geekbrains.githubclient.mvp.model.cache;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IGithubReposCache {
    Single<List<GithubRepo>> getRepos(GithubUser githubUser);

    Completable insertUsers(List<GithubRepo> githubRepos);

}
