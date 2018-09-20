package com.nicoletanetedu.demoapp.model;

import android.util.Log;

import com.nicoletanetedu.demoapp.MainApplication;
import com.nicoletanetedu.demoapp.model.pojos.Forks;
import com.nicoletanetedu.demoapp.model.pojos.Repository;
import com.nicoletanetedu.demoapp.model.pojos.Stargazers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Parser {
   private static List<Repository>repositories = null;
   private static List<Forks>forks = null;
    private static List<Stargazers>stargazers = null;

    private static List<Listener<Repository>> repoListener = new ArrayList<>();

    public static void getRepos(final Listener<Repository>listener){
        Call<List<Repository>> call = MainApplication.getApiInterface().getRepositories(20,"token OAUTH_TOKEN" );
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> models = new ArrayList<>();

                if (response.body()!=null) {
                    for(int i=0;i<20;i++) {
                        models.add(response.body().get(i));
                    }
                }
                    if (repositories == null) {
                        repositories = models;
                    } else {
                        repositories.clear();
                        repositories.addAll(models);
                    }
                    listener.onDataReceive(Parser.clone(repositories));
                    Iterator<Listener<Repository>> it = repoListener.iterator();
                    while (it.hasNext()) {
                        try {
                            it.next().onDataReceive(Parser.clone(repositories));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        it.remove();
                    }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                listener.onDataReceive(null);

                Log.e("ERRO", t.getMessage());

            }
        });
    }

    public static void getForksList(String name, String repoName, final Listener<Forks>listener){
        Call<List<Forks>> call = MainApplication.getApiInterface().getForks(name, repoName,"token OAUTH_TOKEN");
        call.enqueue(new Callback<List<Forks>>() {
            @Override
            public void onResponse(Call<List<Forks>> call, Response<List<Forks>> response) {
                List<Forks> models = new ArrayList<>();

                if (response.body()!=null) {
                   models = response.body();
                }
                if (forks == null) {
                    forks = models;
                } else {
                    forks.clear();
                    forks.addAll(models);
                }
                listener.onDataReceive(Parser.clone(forks));
                Iterator<Listener<Repository>> it = repoListener.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onDataReceive(Parser.clone(forks));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    it.remove();
                }
            }

            @Override
            public void onFailure(Call<List<Forks>> call, Throwable t) {
                listener.onDataReceive(null);
                Log.e("ERRO", t.getMessage());
            }
        });
    }

    public static void getStargazersForRepo(String repoName, String username, final NumberListener listener){

        Call<List<Stargazers>> call2 = MainApplication.getApiInterface().getStargazers(username, repoName, "token OAUTH_TOKEN");
        call2.enqueue(new Callback<List<Stargazers>>() {
            @Override
            public void onResponse(Call<List<Stargazers>> call, Response<List<Stargazers>> response) {
                if(response.body()!=null) {
                    stargazers = response.body();
                    listener.onDataReceived(stargazers.size());
                }else{
                    listener.onDataReceived(0);
                }
            }

            @Override
            public void onFailure(Call<List<Stargazers>> call, Throwable t) {
                Log.e("ERRORFORKS", t.getMessage());
            }
        });
    }


    private static List clone(List list) {
        List clone = new ArrayList(list.size());
        for (Object o : list) {
            clone.add(o);
        }
        return clone;
    }


   public interface Listener<T>{
        void onDataReceive(List<T>list);
   }

    public interface NumberListener {
        void onDataReceived(int response);
    }
}
