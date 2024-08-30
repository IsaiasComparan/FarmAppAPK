package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnTreeHistoryListener;
import com.wamessage.plantapp_plantidentifier.models.Tree;
import java.util.List;


public class HistoryTreeAdapter extends RecyclerView.Adapter<HistoryTreeAdapter.MorePlantHolder> {
    private final OnTreeHistoryListener onTreeHistoryListener;
    private final List<Tree> trees;

    public HistoryTreeAdapter(List<Tree> trees, OnTreeHistoryListener onTreeHistoryListener) {
        this.trees = trees;
        this.onTreeHistoryListener = onTreeHistoryListener;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        List<Tree> list = this.trees;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override 
    public void onBindViewHolder(final MorePlantHolder holder, int position) {
        final Tree tree = this.trees.get(position);
        holder.namePlant.setText(tree.getName());
        holder.score.setText("%" + tree.getScore());
        byte[] image = tree.getImage();
        if (image != null) {
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
        } else {
            holder.img.setImageResource(R.drawable.icon_holder);
        }
        if (tree.getFavorited().booleanValue()) {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
        holder.img_favorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryTreeAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                HistoryTreeAdapter.this.m964x73e7b761(tree, holder, view);
            }
        });
        holder.img_trash.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryTreeAdapter$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                HistoryTreeAdapter.this.m965xd4ce80(tree, view);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryTreeAdapter$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                HistoryTreeAdapter.this.m966x8dc1e59f(tree, view);
            }
        });
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-HistoryTreeAdapter  reason: not valid java name */
    public  void m964x73e7b761(Tree tree, MorePlantHolder morePlantHolder, View view) {
        this.onTreeHistoryListener.onItemHistoryFavorite(tree);
        if (tree.getFavorited().booleanValue()) {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
    }

    
    /* renamed from: lambda$onBindViewHolder$1$com-exorium-plant_recognition-adapter-HistoryTreeAdapter  reason: not valid java name */
    public  void m965xd4ce80(Tree tree, View view) {
        this.onTreeHistoryListener.onItemHistoryDelete(tree);
    }

    
    /* renamed from: lambda$onBindViewHolder$2$com-exorium-plant_recognition-adapter-HistoryTreeAdapter  reason: not valid java name */
    public  void m966x8dc1e59f(Tree tree, View view) {
        this.onTreeHistoryListener.onItemHistoryClick(tree);
    }

    
    public static class MorePlantHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ImageView img_favorite;
        public ImageView img_trash;
        public TextView namePlant;
        public TextView score;

        public MorePlantHolder(View itemView) {
            super(itemView);
            this.namePlant = (TextView) itemView.findViewById(R.id.txtInfoPlant_Name);
            this.score = (TextView) itemView.findViewById(R.id.txtInfoPlant_Score);
            this.img = (ImageView) itemView.findViewById(R.id.imgPlantHistory);
            this.img_favorite = (ImageView) itemView.findViewById(R.id.img_favorite);
            this.img_trash = (ImageView) itemView.findViewById(R.id.img_trash);
        }
    }
}
