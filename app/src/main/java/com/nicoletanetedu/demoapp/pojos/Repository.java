package com.nicoletanetedu.demoapp.pojos;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("name")
    public String repoName;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("description")
    public String repoDescription;
    @SerializedName("owner")
    public Owner owner;

}
