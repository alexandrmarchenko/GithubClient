package com.geekbrains.githubclient.mvp.presenter;

import android.util.Log;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.model.repo.IGithubRepos;
import com.geekbrains.githubclient.mvp.presenter.list.IRepoListPresenter;
import com.geekbrains.githubclient.mvp.view.RepoItemView;
import com.geekbrains.githubclient.mvp.view.UserInfoView;
import com.geekbrains.githubclient.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UserInfoPresenter extends MvpPresenter<UserInfoView> {
    private static final String TAG = UserInfoPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    private final Router mRouter;
    private GithubUser mGithubUser;
    private final IGithubRepos mUsersRepo;
    private final Scheduler mScheduler;

    public UserInfoPresenter(GithubUser githubUser, Router router, IGithubRepos usersRepo, Scheduler scheduler) {
        mGithubUser = githubUser;
        mRouter = router;
        mUsersRepo = usersRepo;
        mScheduler = scheduler;
    }

    private class ReposListPresenter implements IRepoListPresenter {
        private List<GithubRepo> mRepos = new ArrayList<>();

        @Override
        public void onItemClick(RepoItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }
            GithubRepo repo = mRepos.get(view.getPos());
            mRouter.navigateTo(new Screens.RepoInfoScreen(repo));
        }

        @Override
        public void bindView(RepoItemView view) {
            GithubRepo repo = mRepos.get(view.getPos());
            view.setName(repo.getName());
        }

        @Override
        public int getCount() {
            return mRepos.size();
        }
    }

    private UserInfoPresenter.ReposListPresenter mRepoListPresenter = new UserInfoPresenter.ReposListPresenter();

    public IRepoListPresenter getPresenter() {
        return mRepoListPresenter;
    }

    public boolean backPressed() {
        mRouter.backTo(new Screens.UsersScreen());
        return true;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init(mGithubUser);
        loadData();
    }

    private void loadData() {
        mUsersRepo.getRepos(mGithubUser.getReposUrl()).observeOn(mScheduler).subscribe(repos -> {
            mRepoListPresenter.mRepos.clear();
            mRepoListPresenter.mRepos.addAll(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }
}
