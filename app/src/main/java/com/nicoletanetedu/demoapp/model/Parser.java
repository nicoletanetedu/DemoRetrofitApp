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
    private static List<Listener<Forks>> forksListener = new ArrayList<>();
    private static List<Listener<Forks>> stargazersListener = new ArrayList<>();

    public static void getRepos(final Listener<Repository>listener){
        Call<List<Repository>> call = MainApplication.getApiInterface().getRepositories(20,"token fe90f925ed7ab0254e9c767e8406139a83b3d3aa");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                Log.e("RESP", response.body() + " ");
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
        Call<List<Forks>> call = MainApplication.getApiInterface().getForks(name, repoName,"token fe90f925ed7ab0254e9c767e8406139a83b3d3aa");
        call.enqueue(new Callback<List<Forks>>() {
            @Override
            public void onResponse(Call<List<Forks>> call, Response<List<Forks>> response) {
                Log.e("RESP", response.body() + " ");
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


    public static void getForks(String repoName, String username, final NumberListener listener){

        Call<List<Forks>> call2 = MainApplication.getApiInterface().getForks(username, repoName, "token fe90f925ed7ab0254e9c767e8406139a83b3d3aa");
        Log.e("CALL", call2.request()+" ");
        call2.enqueue(new Callback<List<Forks>>() {
            @Override
            public void onResponse(Call<List<Forks>> call, Response<List<Forks>> response) {
                if(response.body()!=null) {
                    forks = response.body();
                    Log.e("FORKS", forks.size() + "");
                    listener.onDataReceived(forks.size());
                }else{
                    Log.e("ELSE", "in else");
                    listener.onDataReceived(0);
                }
            }

            @Override
            public void onFailure(Call<List<Forks>> call, Throwable t) {
                Log.e("ERRORFORKS", t.getMessage());

                listener.onDataReceived(0);


            }
        });
    }


    public static void getStargazersForRepo(String repoName, String username, final NumberListener listener){

        Call<List<Stargazers>> call2 = MainApplication.getApiInterface().getStargazers(username, repoName, "token fe90f925ed7ab0254e9c767e8406139a83b3d3aa");
//        Log.e("CALL", call2.request()+" "+repositories.get(i).fullName);
        call2.enqueue(new Callback<List<Stargazers>>() {
            @Override
            public void onResponse(Call<List<Stargazers>> call, Response<List<Stargazers>> response) {
                if(response.body()!=null) {
                    stargazers = response.body();
                    Log.e("FORKS", stargazers.size() + "");
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
