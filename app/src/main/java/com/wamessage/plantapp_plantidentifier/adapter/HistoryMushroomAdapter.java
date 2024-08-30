package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnMushroomHistoryListener;
import com.wamessage.plantapp_plantidentifier.models.Mushroom;
import java.util.List;


public class HistoryMushroomAdapter extends RecyclerView.Adapter<HistoryMushroomAdapter.MorePlantHolder> {
    private final List<Mushroom> mushrooms;
    private final OnMushroomHistoryListener onMushroomHistoryListener;

    public HistoryMushroomAdapter(List<Mushroom> mushrooms, OnMushroomHistoryListener onMushroomHistoryListener) {
        this.mushrooms = mushrooms;
        this.onMushroomHistoryListener = onMushroomHistoryListener;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        List<Mushroom> list = this.mushrooms;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override 
    public void onBindViewHolder(final MorePlantHolder holder, int position) {
        final Mushroom mushroom = this.mushrooms.get(position);
        holder.namePlant.setText(mushroom.getName());
        holder.score.setText("%" + mushroom.getScore());
        byte[] image = mushroom.getImage();
        if (mushroom.getImage() != null) {
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(mushroom.getImage(), 0, image.length));
        } else {
            holder.img.setImageResource(R.drawable.icon_holder);
        }
        if (mushroom.getFavorited().booleanValue()) {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
        holder.img_favorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryMushroomAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                HistoryMushroomAdapter.this.m955xea1ef167(mushroom, holder, view);
            }
        });
        holder.img_trash.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryMushroomAdapter$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                HistoryMushroomAdapter.this.m956x7e5d6106(mushroom, view);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryMushroomAdapter$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                HistoryMushroomAdapter.this.m957x129bd0a5(mushroom, view);
            }
        });
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-HistoryMushroomAdapter  reason: not valid java name */
    public  void m955xea1ef167(Mushroom mushroom, MorePlantHolder morePlantHolder, View view) {
        this.onMushroomHistoryListener.onItemHistoryFavorite(mushroom);
        if (mushroom.getFavorited().booleanValue()) {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
    }

    
    /* renamed from: lambda$onBindViewHolder$1$com-exorium-plant_recognition-adapter-HistoryMushroomAdapter  reason: not valid java name */
    public  void m956x7e5d6106(Mushroom mushroom, View view) {
        this.onMushroomHistoryListener.onItemHistoryDelete(mushroom);
    }

    
    /* renamed from: lambda$onBindViewHolder$2$com-exorium-plant_recognition-adapter-HistoryMushroomAdapter  reason: not valid java name */
    public  void m957x129bd0a5(Mushroom mushroom, View view) {
        this.onMushroomHistoryListener.onItemHistoryClick(mushroom);
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
