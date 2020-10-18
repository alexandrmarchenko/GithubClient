package com.geekbrains.githubclient.mvp.model.repo.retrofit;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.entity.room.Database;
import com.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;

public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    final IDataSource api;
    final INetworkStatus networkStatus;
    final Database db;

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, Database db) {
        this.api = api;
        this.networkStatus = status;
        this.db = db;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOnline)-> {
            if (isOnline) {
                return api.getUsers().flatMap((users) -> {
                    return Single.fromCallable(()-> {
                        List<RoomGithubUser> roomGithubUsers = new ArrayList<>();

                        for (GithubUser user: users) {
                            RoomGithubUser roomUser = new RoomGithubUser(user.getId(),
                                    user.getLogin(),
                                    user.getAvatarUrl(),
                                    user.getReposUrl());

                            roomGithubUsers.add(roomUser);
                        }

                        db.userDao().insert(roomGithubUsers);
                        return users;
                    });
                });

            } else {
                return Single.fromCallable(()-> {
                    List<RoomGithubUser> roomGithubUsers = db.userDao().getAll();

                    List<GithubUser> users = new ArrayList<>();

                    for (RoomGithubUser roomGithubUser : roomGithubUsers) {
                        GithubUser githubUser = new GithubUser(roomGithubUser.getId(),
                                roomGithubUser.getLogin(),
                                roomGithubUser.getAvatarUrl(),
                                roomGithubUser.getReposUrl());

                        users.add(githubUser);
                    }

                    return users;
                });
            }
        }).subscribeOn(Schedulers.io());

        //return api.getUsers().subscribeOn(Schedulers.io());
    }
}
