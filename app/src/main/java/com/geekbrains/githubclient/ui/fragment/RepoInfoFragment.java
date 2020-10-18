package com.geekbrains.githubclient.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekbrains.githubclient.GithubApplication;
import com.geekbrains.githubclient.databinding.FragmentRepoInfoBinding;
import com.geekbrains.githubclient.mvp.model.entity.GithubRepo;
import com.geekbrains.githubclient.mvp.presenter.RepoInfoPresenter;
import com.geekbrains.githubclient.mvp.view.RepoInfoView;
import com.geekbrains.githubclient.ui.BackButtonListener;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Router;

public class RepoInfoFragment extends MvpAppCompatFragment implements RepoInfoView, BackButtonListener {

    private FragmentRepoInfoBinding binding;

    private static final String ARG_PARAM1 = "githubRepo";

    public RepoInfoFragment() {
    }

    @InjectPresenter
    RepoInfoPresenter mPresenter;

    @ProvidePresenter
    RepoInfoPresenter provideRepoInfoPresenter() {
        GithubRepo mGithubRepo = getArguments().getParcelable(ARG_PARAM1);
        Router router = GithubApplication.INSTANCE.getRouter();

        return new RepoInfoPresenter(mGithubRepo, router);
    }

    public static RepoInfoFragment newInstance(GithubRepo githubRepo) {
        RepoInfoFragment fragment = new RepoInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, githubRepo);
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
        binding = FragmentRepoInfoBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void init(GithubRepo githubRepo) {
        binding.name.setText(githubRepo.getName());
        binding.forksCount.setText(githubRepo.getForks());
    }

    @Override
    public boolean backPressed() {
        mPresenter.backPressed();
        return true;
    }
}