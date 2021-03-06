package com.geekbrains.githubclient.navigation;

import androidx.fragment.app.Fragment;

import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.ui.fragment.RepoInfoFragment;
import com.geekbrains.githubclient.ui.fragment.UserInfoFragment;
import com.geekbrains.githubclient.ui.fragment.UsersFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class UsersScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new UsersFragment();
        }

    }

    public static class UserInfoScreen extends SupportAppScreen {

        private GithubUser githubUser;

        public UserInfoScreen(GithubUser githubUser) {
            this.githubUser = githubUser;
        }

        @Override
        public Fragment getFragment() {
            return UserInfoFragment.newInstance(githubUser);
        }

    }

    public static class RepoInfoScreen extends SupportAppScreen {

        private GithubRepo githubRepo;

        public RepoInfoScreen(GithubRepo githubRepo) {
            this.githubRepo = githubRepo;
        }

        @Override
        public Fragment getFragment() {
            return RepoInfoFragment.newInstance(githubRepo);
        }

    }

}
