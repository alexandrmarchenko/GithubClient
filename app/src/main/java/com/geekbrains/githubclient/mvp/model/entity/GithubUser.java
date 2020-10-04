package com.geekbrains.githubclient.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class GithubUser implements Parcelable {
    private String mLogin;

    public GithubUser(String login) {
        mLogin = login;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mLogin);
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
