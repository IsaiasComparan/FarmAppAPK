package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnPlantHistoryListener;
import com.wamessage.plantapp_plantidentifier.models.Plant;
import java.util.List;


public class HistoryPlantAdapter extends RecyclerView.Adapter<HistoryPlantAdapter.MorePlantHolder> {
    private final OnPlantHistoryListener onPlantHistoryListener;
    private final List<Plant> plants;

    public HistoryPlantAdapter(List<Plant> plants, OnPlantHistoryListener onPlantHistoryListener) {
        this.plants = plants;
        this.onPlantHistoryListener = onPlantHistoryListener;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        List<Plant> list = this.plants;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override 
    public void onBindViewHolder(final MorePlantHolder holder, int position) {
        final Plant plant = this.plants.get(position);
        holder.namePlant.setText(plant.getName());
        holder.score.setText("%" + plant.getScore());
        byte[] image = plant.getImage();
        if (plant.getImage() != null) {
            holder.img.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(plant.getImage(), 0, image.length), 120, 120, false));
        } else {
            holder.img.setImageResource(R.drawable.icon_holder);
        }
        if (plant.getFavorited().booleanValue()) {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
        holder.img_favorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryPlantAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                HistoryPlantAdapter.this.m958x4cd9f6d4(plant, holder, view);
            }
        });
        holder.img_trash.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryPlantAdapter$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                HistoryPlantAdapter.this.m959x5d8fc395(plant, view);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryPlantAdapter$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                HistoryPlantAdapter.this.m960x6e459056(plant, view);
            }
        });
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-HistoryPlantAdapter  reason: not valid java name */
    public  void m958x4cd9f6d4(Plant plant, MorePlantHolder morePlantHolder, View view) {
        this.onPlantHistoryListener.onItemHistoryFavorite(plant);
        if (plant.getFavorited().booleanValue()) {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
    }

    
    /* renamed from: lambda$onBindViewHolder$1$com-exorium-plant_recognition-adapter-HistoryPlantAdapter  reason: not valid java name */
    public  void m959x5d8fc395(Plant plant, View view) {
        this.onPlantHistoryListener.onItemHistoryDelete(plant);
    }

    
    /* renamed from: lambda$onBindViewHolder$2$com-exorium-plant_recognition-adapter-HistoryPlantAdapter  reason: not valid java name */
    public  void m960x6e459056(Plant plant, View view) {
        this.onPlantHistoryListener.onItemHistoryClick(plant);
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
