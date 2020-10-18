package com.geekbrains.githubclient.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GithubUserRepo {
    private List<GithubUser> mRepositories = new ArrayList<>(Arrays.asList(
            new GithubUser("login1", "", "", "")
    ));

    public Observable<GithubUser> getUsers() {
        return Observable.fromIterable(mRepositories);
    }
}
