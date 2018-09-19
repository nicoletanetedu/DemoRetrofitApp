package com.nicoletanetedu.demoapp.pojos;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("avatar_url")
    public String image;
    @SerializedName("login")
    public String name;

}
