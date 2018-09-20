package com.nicoletanetedu.demoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nicoletanetedu.demoapp.R;
import com.nicoletanetedu.demoapp.model.pojos.Repository;
import com.nicoletanetedu.demoapp.views.viewHolders.RepositoryViewHolder;
import com.nicoletanetedu.utils.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private List<Repository> repositories ;
    private LayoutInflater inflater;
    private Context context;
    private OnClickListener listener;

    public RepositoryListAdapter(Context context) {
        this.context = context;
        this.repositories = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<Repository> repositoryList){
        this.repositories.addAll(repositoryList);
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener clickListener){
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_repo_list, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, final int position) {
        final Repository repo = repositories.get(position);
        holder.repoName.setText(repo.getRepoName());
        holder.ownername.setText(repo.getOwner().name);
        holder.description.setText(repo.getRepoDescription());
        Glide.with(context)
                .load(repo.getOwner().image)
                .thumbnail(0.5f)
                .error(R.drawable.empty_image)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.ownerAvatar);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position, repo);
            }
        });

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
