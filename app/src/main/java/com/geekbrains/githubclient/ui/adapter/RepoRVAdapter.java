package com.geekbrains.githubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geekbrains.githubclient.R;
import com.geekbrains.githubclient.mvp.presenter.list.IRepoListPresenter;
import com.geekbrains.githubclient.mvp.view.RepoItemView;

public class RepoRVAdapter extends RecyclerView.Adapter<RepoRVAdapter.ViewHolder> {
    private IRepoListPresenter mPresenter;

    public RepoRVAdapter(IRepoListPresenter presenter) {
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repoView = inflater.inflate(R.layout.item_repo, parent, false);

        RepoRVAdapter.ViewHolder viewHolder = new RepoRVAdapter.ViewHolder(repoView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onItemClick(holder);
            }
        });

        mPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCount();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements RepoItemView {
        int position;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_name);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setName(String name) {
            textView.setText(name);
        }
    }
}
