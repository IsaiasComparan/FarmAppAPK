package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnPlantHealthHistoryListener;
import com.wamessage.plantapp_plantidentifier.models.PlantHealth;
import java.util.List;


public class HistoryPlantHealthAdapter extends RecyclerView.Adapter<HistoryPlantHealthAdapter.MorePlantHolder> {
    private OnPlantHealthHistoryListener onPlantHealthHistoryListener;
    private List<PlantHealth> plantHealths;

    public HistoryPlantHealthAdapter(List<PlantHealth> plantHealths, OnPlantHealthHistoryListener onPlantHealthHistoryListener) {
        this.plantHealths = plantHealths;
        this.onPlantHealthHistoryListener = onPlantHealthHistoryListener;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        List<PlantHealth> list = this.plantHealths;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override 
    public void onBindViewHolder(final MorePlantHolder holder, int position) {
        final PlantHealth plantHealth = this.plantHealths.get(position);
        holder.namePlant.setText(plantHealth.getName());
        holder.score.setText("%" + plantHealth.getScore());
        holder.img.setImageBitmap(BitmapFactory.decodeByteArray(plantHealth.getImage(), 0, plantHealth.getImage().length));
        if (plantHealth.getFavorited().booleanValue()) {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
        holder.img_favorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryPlantHealthAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                HistoryPlantHealthAdapter.this.m961x1595f518(plantHealth, holder, view);
            }
        });
        holder.img_trash.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryPlantHealthAdapter$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                HistoryPlantHealthAdapter.this.m962x63556d19(plantHealth, view);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryPlantHealthAdapter$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                HistoryPlantHealthAdapter.this.m963xb114e51a(plantHealth, view);
            }
        });
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-HistoryPlantHealthAdapter  reason: not valid java name */
    public  void m961x1595f518(PlantHealth plantHealth, MorePlantHolder morePlantHolder, View view) {
        this.onPlantHealthHistoryListener.onItemHistoryFavorite(plantHealth);
        if (plantHealth.getFavorited().booleanValue()) {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
    }

    
    /* renamed from: lambda$onBindViewHolder$1$com-exorium-plant_recognition-adapter-HistoryPlantHealthAdapter  reason: not valid java name */
    public  void m962x63556d19(PlantHealth plantHealth, View view) {
        this.onPlantHealthHistoryListener.onItemHistoryDelete(plantHealth);
    }

    
    /* renamed from: lambda$onBindViewHolder$2$com-exorium-plant_recognition-adapter-HistoryPlantHealthAdapter  reason: not valid java name */
    public  void m963xb114e51a(PlantHealth plantHealth, View view) {
        this.onPlantHealthHistoryListener.onItemHistoryClick(plantHealth);
    }

    
    public class MorePlantHolder extends RecyclerView.ViewHolder {
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
