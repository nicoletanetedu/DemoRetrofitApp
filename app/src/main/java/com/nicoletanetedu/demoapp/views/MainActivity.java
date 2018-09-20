package com.nicoletanetedu.demoapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nicoletanetedu.demoapp.R;
import com.nicoletanetedu.demoapp.adapters.RepositoryListAdapter;
import com.nicoletanetedu.demoapp.model.Parser;
import com.nicoletanetedu.demoapp.model.pojos.Repository;
import com.nicoletanetedu.utils.OnClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private RecyclerView repoList;
    private RepositoryListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecycler();

        Parser.getRepos(new Parser.Listener<Repository>() {
            @Override
            public void onDataReceive(List<Repository> list) {
                if(list!=null && list.size()>0){
                    adapter.setList(list);
                }else{
                    Log.e("NOTDATA", "there is no data ");
                }
            }
        });
    }

    public void setRecycler(){
        repoList = findViewById(R.id.repoList);
        repoList.setLayoutManager(new LinearLayoutManager(this));
        adapter= new RepositoryListAdapter(this);
        adapter.setOnClickListener(this);
        repoList.setAdapter(adapter);


    }

    @Override
    public void onItemClick(int position, Repository repository) {

        openDetail(repository);
    }

    private void openDetail(Repository repository){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("repository", repository);
        intent.putExtra("image", repository.getOwner().image);
        startActivity(intent);
    }
}
