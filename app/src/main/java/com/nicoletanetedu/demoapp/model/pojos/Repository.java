package com.nicoletanetedu.demoapp.model.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String repoName;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("description")
    private String repoDescription;
    @SerializedName("forks_url")
    private String forksUrl;
    @SerializedName("stargazers_url")
    private String stargazersUrl;
    @SerializedName("owner")
    private Owner owner;

    protected Repository(Parcel in) {
        id = in.readInt();
        repoName = in.readString();
        fullName = in.readString();
        repoDescription = in.readString();
        forksUrl = in.readString();
        stargazersUrl = in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(repoName);
        parcel.writeString(fullName);
        parcel.writeString(repoDescription);
        parcel.writeString(forksUrl);
        parcel.writeString(stargazersUrl);
    }

    public Repository(int id, String repoName, String fullName, String repoDescription, String forksUrl, String stargazersUrl, Owner owner) {
        this.id = id;
        this.repoName = repoName;
        this.fullName = fullName;
        this.repoDescription = repoDescription;
        this.forksUrl = forksUrl;
        this.stargazersUrl = stargazersUrl;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRepoDescription() {
        return repoDescription;
    }

    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    public String getStargazersUrl() {
        return stargazersUrl;
    }

    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
