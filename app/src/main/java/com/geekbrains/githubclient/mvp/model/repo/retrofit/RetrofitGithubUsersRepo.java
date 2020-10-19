package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.cache.IGithubUsersRepoCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    final IDataSource api;
    final INetworkStatus networkStatus;
    private final IGithubUsersRepoCache cache;

    public PublishSubject<List<GithubUser>> subject = PublishSubject.create();

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, IGithubUsersRepoCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
        this.cache.subscribe(subject);
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.getUsers().flatMap((users) -> {
                    subject.onNext(users);
                    return Single.fromCallable(() -> users);
                });

            } else {
                return cache.getUsers();
            }
        }).subscribeOn(Schedulers.io());

        //return api.getUsers().subscribeOn(Schedulers.io());
    }
}
