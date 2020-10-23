package com.geekbrains.githubclient.mvp.model.cache;

import com.geekbrains.githubclient.mvp.model.entity.GithubUser;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface IGithubUsersRepoCache {
    Single<List<GithubUser>> getUsers();

    Completable insertUsers(List<GithubUser> githubUsers);

}
