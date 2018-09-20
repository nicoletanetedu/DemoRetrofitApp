package com.nicoletanetedu.demoapp.views.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nicoletanetedu.demoapp.R;

public class ForksViewHolder extends RecyclerView.ViewHolder{
    public TextView id, forks, stargazers, watchers, openIssues;

    public ForksViewHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        forks = itemView.findViewById(R.id.forks);
        stargazers = itemView.findViewById(R.id.stargazers);
        watchers= itemView.findViewById(R.id.watchersNo);
        openIssues = itemView.findViewById(R.id.openIssues);
    }
}
