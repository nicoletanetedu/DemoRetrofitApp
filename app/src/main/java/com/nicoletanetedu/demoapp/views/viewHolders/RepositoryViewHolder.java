package com.nicoletanetedu.demoapp.views.viewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoletanetedu.demoapp.R;

public class RepositoryViewHolder extends RecyclerView.ViewHolder{
    public TextView ownername, repoName, description;
    public ImageView ownerAvatar;
    public CardView item;

    public RepositoryViewHolder(View itemView) {
        super(itemView);
        ownername= itemView.findViewById(R.id.ownerName);
        repoName = itemView.findViewById(R.id.repoName);
        description = itemView.findViewById(R.id.description);
        ownerAvatar = itemView.findViewById(R.id.avatar);
        item = itemView.findViewById(R.id.item);
    }
}
