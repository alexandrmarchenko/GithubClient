package com.geekbrains.githubclient.mvp.model.cache.room;

import com.geekbrains.githubclient.mvp.model.cache.IGithubReposCache;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.entity.room.Database;
import com.geekbrains.githubclient.mvp.model.entity.room.RoomGithubRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class RoomGithubReposCache implements IGithubReposCache {
    private static final String TAG = RoomGithubReposCache.class.getSimpleName();
    private final Database db;

    public RoomGithubReposCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<List<GithubRepo>> getRepos(GithubUser githubUser) {
        return Single.fromCallable(() -> {
            List<RoomGithubRepository> roomGithubRepos = db.repositoryDao().findByUser(githubUser.getId());

            List<GithubRepo> repos = new ArrayList<>();

            for (RoomGithubRepository roomGithubRepo : roomGithubRepos) {
                GithubRepo githubRepo = new GithubRepo(roomGithubRepo.getId(),
                        roomGithubRepo.getName(),
                        roomGithubRepo.getForksCount(),
                        githubUser);

                repos.add(githubRepo);
            }

            return repos;
        });
    }

    @Override
    public Completable insertUsers(List<GithubRepo> githubRepos) {
        return Completable.fromAction(() -> {
            List<RoomGithubRepository> roomGithubRepos = new ArrayList<>();

            for (GithubRepo repo : githubRepos) {
                RoomGithubRepository roomRepo = new RoomGithubRepository(repo.getId(),
                        repo.getName(),
                        repo.getForks(),
                        repo.getOwner().getId());

                roomGithubRepos.add(roomRepo);
            }

            db.repositoryDao().insert(roomGithubRepos);
        });
    }

}
