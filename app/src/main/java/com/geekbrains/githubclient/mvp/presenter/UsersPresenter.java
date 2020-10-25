package com.geekbrains.githubclient.mvp.presenter;

import android.util.Log;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter;
import com.geekbrains.githubclient.mvp.view.UserItemView;
import com.geekbrains.githubclient.mvp.view.UsersView;
import com.geekbrains.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private static final String TAG = UsersPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    @Inject
    IGithubUsersRepo usersRepo;
    @Inject
    Router router;
    @Inject
    Scheduler scheduler;

    public UsersPresenter() {
        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    public class UsersListPresenter implements IUserListPresenter {
        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            // Nothing to do here
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }
            GithubUser user = users.get(view.getPos());
            router.navigateTo(new Screens.UserInfoScreen(user));
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
            view.loadAvatar(user.getAvatarUrl());
        }

        @Override
        public int getCount() {
            return users.size();
        }
    }

    private UsersPresenter.UsersListPresenter userListPresenter = new UsersPresenter.UsersListPresenter();

    public IUserListPresenter getPresenter() {
        return userListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }

    private void loadData() {
        usersRepo.getUsers().observeOn(scheduler).subscribe(repos -> {
            userListPresenter.users.clear();
            userListPresenter.users.addAll(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

}
