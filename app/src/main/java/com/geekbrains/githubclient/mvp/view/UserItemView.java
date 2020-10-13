package com.geekbrains.githubclient.mvp.view;

public interface UserItemView extends IItemView {
    void setLogin(String text);
    void loadAvatar(String url);
}
