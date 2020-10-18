package com.geekbrains.githubclient.mvp.model.repo;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IGithubRepos {
    Single<List<GithubRepo>> getRepos(String url);
}
