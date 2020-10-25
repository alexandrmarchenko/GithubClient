package com.geekbrains.githubclient.mvp.model.cache.room;

import com.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.entity.room.Database;
import com.geekbrains.githubclient.mvp.model.entity.room.RoomGithubUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class RoomGithubUsersCache implements IGithubUsersCache {
    private static final String TAG = RoomGithubUsersCache.class.getSimpleName();
    private Database db;

    public RoomGithubUsersCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return Single.fromCallable(() -> {
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

    @Override
    public Completable insertUsers(List<GithubUser> githubUsers) {
        return Completable.fromAction(() -> {
            List<RoomGithubUser> roomGithubUsers = new ArrayList<>();

            for (GithubUser user : githubUsers) {
                RoomGithubUser roomUser = new RoomGithubUser(user.getId(),
                        user.getLogin(),
                        user.getAvatarUrl(),
                        user.getReposUrl());

                roomGithubUsers.add(roomUser);
            }

            db.userDao().insert(roomGithubUsers);
        });

    }
}
