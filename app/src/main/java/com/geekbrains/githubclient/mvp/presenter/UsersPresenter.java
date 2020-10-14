package com.geekbrains.githubclient.mvp.presenter;

import android.util.Log;

import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.repo.IGithubUsersRepo;
import com.geekbrains.githubclient.mvp.presenter.list.IUserListPresenter;
import com.geekbrains.githubclient.mvp.view.UserItemView;
import com.geekbrains.githubclient.mvp.view.UsersView;
import com.geekbrains.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView> {
    private static final String TAG = UsersPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    private final IGithubUsersRepo mUsersRepo;
    private final Router mRouter;
    private final Scheduler mScheduler;

    public UsersPresenter(Scheduler scheduler, IGithubUsersRepo usersRepo, Router router) {
        mScheduler = scheduler;
        mUsersRepo = usersRepo;
        mRouter = router;
    }

    private class UsersListPresenter implements IUserListPresenter {
        private List<GithubUser> mUsers = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            // Nothing to do here
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }
            GithubUser user = mUsers.get(view.getPos());
            mRouter.navigateTo(new Screens.UserInfoScreen(user));
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = mUsers.get(view.getPos());
            view.setLogin(user.getLogin());
            view.loadAvatar(user.getAvatarUrl());
        }

        @Override
        public int getCount() {
            return mUsers.size();
        }
    }

    private UsersPresenter.UsersListPresenter mUserListPresenter = new UsersPresenter.UsersListPresenter();

    public IUserListPresenter getPresenter() {
        return mUserListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }

    private void loadData() {
//        mUsersRepo.getUsers().subscribe(githubUser ->
//                mUserListPresenter.mUsers.add(githubUser)
//        );
//        List<GithubUser> users = mUsersRepo.getUsers();
//        mUserListPresenter.mUsers.addAll(users);
//        getViewState().updateList();
        mUsersRepo.getUsers().observeOn(mScheduler).subscribe(repos -> {
            mUserListPresenter.mUsers.clear();
            mUserListPresenter.mUsers.addAll(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }

    public boolean backPressed() {
        mRouter.exit();
        return true;
    }

}
