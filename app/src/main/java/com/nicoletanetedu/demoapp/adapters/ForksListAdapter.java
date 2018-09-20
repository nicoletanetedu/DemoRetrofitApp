package com.nicoletanetedu.demoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicoletanetedu.demoapp.R;
import com.nicoletanetedu.demoapp.model.pojos.Forks;
import com.nicoletanetedu.demoapp.views.viewHolders.ForksViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ForksListAdapter extends RecyclerView.Adapter<ForksViewHolder> {
    private List<Forks>forks;
    private LayoutInflater inflater;
    private Context context;

    public ForksListAdapter(Context context) {
        this.context = context;
        this.forks = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<Forks> forksList){
        this.forks.addAll(forksList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ForksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_forks_list, parent, false);
        return new ForksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForksViewHolder holder, int position) {
        Forks fork = forks.get(position);
        holder.id.setText(fork.getId());
        holder.forks.setText(new StringBuilder().append(context.getString(R.string.forks)).append(fork.getForksCount()));
        holder.stargazers.setText(new StringBuilder().append(context.getString(R.string.stargazers)).append(fork.getStargazersCount()));
        holder.watchers.setText(new StringBuilder().append(context.getString(R.string.watchers)).append(fork.getWatchersCount()));
        holder.openIssues.setText(new StringBuilder().append(context.getString(R.string.opened_issues)).append(fork.getOpenIssues()));

    }

    @Override
    public int getItemCount() {
        return forks.size();
    }
}
