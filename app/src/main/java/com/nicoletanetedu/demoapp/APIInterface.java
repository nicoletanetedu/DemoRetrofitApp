package com.nicoletanetedu.demoapp;

import com.nicoletanetedu.demoapp.pojos.Forks;
import com.nicoletanetedu.demoapp.pojos.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface APIInterface {

    @GET("/repositories")
    Call<List<Repository>> getRepositories();

    @GET("/repos/{full_name}/forks")
    Call<List<Forks>> getForks(@Path("full_name") String full_name);

//    @GET("/api/users?")
//    Call<UserList> doGetUserList(@Query("page") String page);
//
//    @FormUrlEncoded
//    @POST("/api/users?")
//    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}
