package com.geekbrains.githubclient.mvp.presenter;

import com.geekbrains.githubclient.mvp.view.MainView;
import com.geekbrains.githubclient.navigation.Screens;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    private Router mRouter;// = GithubApplication.INSTANCE.getRouter();

    public MainPresenter(Router rounter) {
        super();
        mRouter = rounter;
    }



    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mRouter.replaceScreen(new Screens.UsersScreen());
    }

    public void backClicked() {
        mRouter.exit();
    }

}
