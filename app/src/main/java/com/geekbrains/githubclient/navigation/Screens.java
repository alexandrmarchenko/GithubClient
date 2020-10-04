package com.geekbrains.githubclient.navigation;

import androidx.fragment.app.Fragment;

import com.geekbrains.githubclient.ui.fragment.UsersFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {
    public static class UsersScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new UsersFragment();
        }
    }

}
