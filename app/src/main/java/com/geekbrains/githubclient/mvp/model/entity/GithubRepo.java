package com.geekbrains.githubclient.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class GithubRepo implements Parcelable {
    @Expose
    String id;
    @Expose
    String name;
    @Expose
    String forks;

    public GithubRepo(String id, String name, String forks) {
        this.id = id;
        this.name = name;
        this.forks = forks;
    }

    protected GithubRepo(Parcel in) {
        id = in.readString();
        name = in.readString();
        forks = in.readString();
    }

    public GithubRepo(String login) {
        name = name;
    }

    public String getName() {
        return name;
    }

    public String getForks() {
        return forks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(forks);
    }

    public static final Parcelable.Creator<GithubRepo> CREATOR = new Parcelable.Creator<GithubRepo>() {

        @Override
        public GithubRepo createFromParcel(Parcel parcel) {
            return new GithubRepo(parcel);
        }

        @Override
        public GithubRepo[] newArray(int i) {
            return new GithubRepo[i];
        }
    };
}
