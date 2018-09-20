package com.nicoletanetedu.demoapp.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nicoletanetedu.demoapp.R;
import com.nicoletanetedu.demoapp.adapters.ForksListAdapter;
import com.nicoletanetedu.demoapp.model.Parser;
import com.nicoletanetedu.demoapp.model.pojos.Forks;
import com.nicoletanetedu.demoapp.model.pojos.Repository;

import java.util.List;


public class DetailActivity extends AppCompatActivity {
    private Repository repository;
    private String imageUrl;
    private TextView ownerName, repoName, repoDescription, forksNo, stargazersNo, watchersNo;
    private ImageView avatar;
    private RecyclerView forksList;
    private ForksListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        instantiateView();
        instantiateList();
        getExtrasFromIntent();
        setFieldsData(repository);


    }

    private void instantiateList() {
        forksList = findViewById(R.id.forksList);
        forksList.setLayoutManager(new LinearLayoutManager(this));
        forksList.setNestedScrollingEnabled(false);
        adapter = new ForksListAdapter(this);
        forksList.setAdapter(adapter);
    }

    private void setFieldsData(Repository repository) {
        repoName.setText(repository.getRepoName());
        String[]name = repository.getFullName().split("/");
        ownerName.setText(name[0]);
        repoDescription.setText(repository.getRepoDescription());

        Glide.with(this)
                .load(imageUrl)
                .thumbnail(0.5f)
                .error(R.drawable.empty_image)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(avatar);

        setForks(name[0], name[1]);
        setStargazers(name[0], name[1]);

    }

    private void setForks(String owner, String repoName){
        Parser.getForksList(repoName, owner, new Parser.Listener<Forks>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataReceive(List<Forks> list) {
                if(list!=null&& list.size()>0) {
                    forksNo.setText(getResources().getString(R.string.forks)+ String.valueOf(list.size()));
                    adapter.setList(list);
                }
            }
        });
    }

    private void setStargazers(String owner, String repoName){
        Parser.getStargazersForRepo(repoName, owner, new Parser.NumberListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataReceived(int response) {
                stargazersNo.setText(getResources().getString(R.string.stargazers) + String.valueOf(response));
            }
        });
    }

    private void getExtrasFromIntent() {
        Intent data = getIntent();
        repository = data.getParcelableExtra("repository");
        imageUrl  = data.getStringExtra("image");
    }

    private void instantiateView() {
        ownerName = findViewById(R.id.ownerNameDetail);
        repoName = findViewById(R.id.repoNameDetail);
        repoDescription = findViewById(R.id.repoDescription);
        forksNo = findViewById(R.id.forksNumber);
        stargazersNo = findViewById(R.id.stargazersNo);
        avatar = findViewById(R.id.avatar);
    }
}
