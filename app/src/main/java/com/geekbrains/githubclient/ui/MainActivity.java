package com.geekbrains.githubclient.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.R;
import com.geekbrains.githubclient.mvp.presenter.MainPresenter;
import com.geekbrains.githubclient.mvp.view.MainView;

import javax.inject.Inject;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    Navigator mNavigator = new SupportAppNavigator(this, getSupportFragmentManager(), R.id.container);

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    MainPresenter presenter;

    @ProvidePresenter
    MainPresenter provideMainPresenter() {

        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        navigatorHolder.setNavigator(mNavigator);
    }

    @Override
    protected void onPause() {
        super.onPause();

        navigatorHolder.removeNavigator();
    }

    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BackButtonListener && ((BackButtonListener) fragment).backPressed()) {
                return;
            }
        }

        presenter.backClicked();
    }
}