package com.geekbrains.githubclient.di.module;

import androidx.room.Room;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.cache.IGithubReposCache;
import com.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.cache.room.RoomGithubReposCache;
import com.geekbrains.githubclient.mvp.model.cache.room.RoomGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.entity.room.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Singleton
    @Provides
    Database database() {
        return Room.databaseBuilder(GithubApplication.getAppContext(), Database.class, Database.DB_NAME)
                .build();
    }

    @Singleton
    @Provides
    IGithubUsersCache usersCache(Database db) {
        return new RoomGithubUsersCache(db);
    }

    @Singleton
    @Provides
    IGithubReposCache userRepositoriesCache(Database db) {
        return new RoomGithubReposCache(db);
    }

}
