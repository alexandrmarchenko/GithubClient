package com.geekbrains.githubclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.databinding.FragmentUserInfoBinding;
import com.geekbrains.githubclient.mvp.model.entity.GithubUser;
import com.geekbrains.githubclient.mvp.presenter.UserInfoPresenter;
import com.geekbrains.githubclient.mvp.view.UserInfoView;
import com.geekbrains.githubclient.ui.BackButtonListener;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Router;

public class UserInfoFragment extends MvpAppCompatFragment implements UserInfoView, BackButtonListener {

    private FragmentUserInfoBinding binding;

    private static final String ARG_PARAM1 = "githubUser";

    public UserInfoFragment() {
    }

    @InjectPresenter
    UserInfoPresenter mPresenter;

    @ProvidePresenter
    UserInfoPresenter provideUserInfoPresenter() {
        GithubUser mGithubUser = getArguments().getParcelable(ARG_PARAM1);
        Router router = GithubApplication.INSTANCE.getRouter();

        return new UserInfoPresenter(mGithubUser, router);
    }

    public static UserInfoFragment newInstance(GithubUser githubUser) {
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, githubUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean backPressed() {
        mPresenter.backPressed();
        return true;
    }

    @Override
    public void init(GithubUser githubUser) {
        binding.userLogin.setText(githubUser.getLogin());
    }
}