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
    int forks;
    @Expose
    GithubUser owner;

    public GithubRepo(String id, String name, int forks, GithubUser owner) {
        this.id = id;
        this.name = name;
        this.forks = forks;
        this.owner = owner;
    }

    protected GithubRepo(Parcel in) {
        id = in.readString();
        name = in.readString();
        forks = in.readInt();
        owner = in.readParcelable(GithubUser.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public GithubUser getOwner() {
        return owner;
    }

    public GithubRepo(String login) {
        name = name;
    }

    public String getName() {
        return name;
    }

    public int getForks() {
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
        parcel.writeInt(forks);
        parcel.writeParcelable(owner, i);
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
