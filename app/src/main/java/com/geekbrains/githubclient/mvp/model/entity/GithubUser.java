package com.geekbrains.githubclient.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class GithubUser implements Parcelable {
    @Expose
    String id;
    @Expose
    String login;
    @Expose
    String avatarUrl;
    @Expose
    String reposUrl;

    public GithubUser(String id, String login, String avatar, String repoUrl) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatar;
        this.reposUrl = repoUrl;
    }

    protected GithubUser(Parcel in) {
        this.id = in.readString();
        this.login = in.readString();
        this.avatarUrl = in.readString();
        this.reposUrl = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
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
        parcel.writeString(id);
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
        parcel.writeString(reposUrl);
    }

    public static final Creator<GithubUser> CREATOR = new Creator<GithubUser>() {

        @Override
        public GithubUser createFromParcel(Parcel parcel) {
            return new GithubUser(parcel);
        }

        @Override
        public GithubUser[] newArray(int i) {
            return new GithubUser[i];
        }
    };
}
