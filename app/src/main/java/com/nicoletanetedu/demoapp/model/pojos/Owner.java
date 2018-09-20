package com.nicoletanetedu.demoapp.model.pojos;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("avatar_url")
    public String image;
    @SerializedName("login")
    public String name;

    public Owner(String image) {
        this.image = image;
    }
}
