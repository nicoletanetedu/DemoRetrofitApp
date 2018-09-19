package com.nicoletanetedu.demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nicoletanetedu.demoapp.pojos.Forks;
import com.nicoletanetedu.demoapp.pojos.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    APIInterface apiInterface;
    List<Repository>repositories ;
    List<Forks>forks = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repositories = new ArrayList<>();
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<Repository>>call = apiInterface.getRepositories();
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                repositories = response.body();
                Log.e("RESP", response.body() + "");
                if (response.body()!=null) {
                    Log.e("REPO", repositories.size() + "");
                    for (Repository repo : repositories) {
                        String[] sparse = repo.fullName.split("/");
                        Call<List<Forks>> call2 = apiInterface.getForks(repo.fullName);
                        Log.e("CALL", call2.request()+"");
                        call2.enqueue(new Callback<List<Forks>>() {
                            @Override
                            public void onResponse(Call<List<Forks>> call, Response<List<Forks>> response) {
                                forks = response.body();
                                Log.e("FORKS", response.body() + "");
                            }

                            @Override
                            public void onFailure(Call<List<Forks>> call, Throwable t) {
                                Log.e("ERRORFORKS", t.getMessage());

                            }
                        });
                    }

//                    Log.e("REPOO", repo.repoName+" "+repo.repoDescription);
                }else{
                    Log.e("NULL", "first response is null");
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.e("ERRO", t.getMessage());

            }
        });




    }
}
