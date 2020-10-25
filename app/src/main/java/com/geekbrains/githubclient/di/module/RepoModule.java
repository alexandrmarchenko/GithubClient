package com.geekbrains.githubclient.di.module;

import com.geekbrains.githubclient.mvp.model.api.IDataSource;
import com.geekbrains.githubclient.mvp.model.cache.IGithubReposCache;
import com.geekbrains.githubclient.mvp.model.cache.IGithubUsersCache;
import com.geekbrains.githubclient.mvp.model.network.INetworkStatus;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepositoriesRepo;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;
import com.geekbrains.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {
    @Singleton
    @Provides
    public IGithubUsersRepo usersRepo(IDataSource api, INetworkStatus status, IGithubUsersCache cache) {
        return new RetrofitGithubUsersRepo(api, status, cache);
    }

    @Singleton
    @Provides
    public IGithubRepositoriesRepo userRepositoriesRepo(IDataSource api, INetworkStatus networkStatus, IGithubReposCache cache) {
        return new RetrofitGithubRepositoriesRepo(api, networkStatus, cache);
    }

}