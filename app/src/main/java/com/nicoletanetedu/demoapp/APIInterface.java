package com.nicoletanetedu.demoapp;

import com.nicoletanetedu.demoapp.model.pojos.Forks;
import com.nicoletanetedu.demoapp.model.pojos.Repository;
import com.nicoletanetedu.demoapp.model.pojos.Stargazers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/repositories")
    Call<List<Repository>> getRepositories(@Query("count") Integer count,@Header("Authorization") String auth);

    @GET("/repos/{name}/{repo_name}/forks")
    Call<List<Forks>> getForks(@Path("repo_name") String name, @Path("name") String repo_name, @Header("Authorization") String auth);

    @GET("/repos/{name}/{repo_name}/stargazers")
    Call<List<Stargazers>> getStargazers(@Path("repo_name") String name, @Path("name") String repo_name, @Header("Authorization") String auth);
}
