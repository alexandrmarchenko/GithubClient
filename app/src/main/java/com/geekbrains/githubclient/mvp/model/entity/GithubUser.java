package com.geekbrains.githubclient.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class GithubUser implements Parcelable {
    @Expose String id;
    @Expose String login;
    @Expose String avatarUrl;
    @Expose String reposUrl;

    public GithubUser(String login) {
        login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setLogin(String login) {
        login = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {

        @Override
        public GithubUser createFromParcel(Parcel parcel) {
            return new GithubUser(parcel.readString());
        }

        @Override
        public GithubUser[] newArray(int i) {
            return new GithubUser[i];
        }
    };
}
