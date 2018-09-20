package com.nicoletanetedu.demoapp.model.pojos;

import com.google.gson.annotations.SerializedName;

public class Stargazers {
    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
