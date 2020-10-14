package com.geekbrains.githubclient.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GithubUserRepo {
    private List<GithubUser> mRepositories = new ArrayList<>(Arrays.asList(new GithubUser("login1"),
            new GithubUser("login2"),
            new GithubUser("login3"),
            new GithubUser("login4"),
            new GithubUser("login5")));

    public Observable<GithubUser> getUsers() {
        return Observable.fromIterable(mRepositories);
    }
}
