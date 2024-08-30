package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnInsectHistoryListener;
import com.wamessage.plantapp_plantidentifier.models.Insect;
import java.util.List;


public class HistoryInsectAdapter extends RecyclerView.Adapter<HistoryInsectAdapter.MorePlantHolder> {
    private final List<Insect> insects;
    private final OnInsectHistoryListener onInsectHistoryListener;

    public HistoryInsectAdapter(List<Insect> insects, OnInsectHistoryListener onInsectHistoryListener) {
        this.insects = insects;
        this.onInsectHistoryListener = onInsectHistoryListener;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        List<Insect> list = this.insects;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override 
    public void onBindViewHolder(final MorePlantHolder holder, int position) {
        final Insect insect = this.insects.get(position);
        holder.namePlant.setText(insect.getName());
        holder.score.setText("%" + insect.getScore());
        byte[] image = insect.getImage();
        if (insect.getImage() != null) {
            holder.img.setImageBitmap(BitmapFactory.decodeByteArray(insect.getImage(), 0, image.length));
        } else {
            holder.img.setImageResource(R.drawable.icon_holder);
        }
        if (insect.getFavorited().booleanValue()) {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            holder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
        holder.img_favorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryInsectAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                HistoryInsectAdapter.this.m952x5bd29ed7(insect, holder, view);
            }
        });
        holder.img_trash.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryInsectAdapter$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                HistoryInsectAdapter.this.m953x61d66a36(insect, view);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.HistoryInsectAdapter$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                HistoryInsectAdapter.this.m954x67da3595(insect, view);
            }
        });
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-HistoryInsectAdapter  reason: not valid java name */
    public  void m952x5bd29ed7(Insect insect, MorePlantHolder morePlantHolder, View view) {
        this.onInsectHistoryListener.onItemHistoryFavorite(insect);
        if (insect.getFavorited().booleanValue()) {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_active);
        } else {
            morePlantHolder.img_favorite.setImageResource(R.drawable.ic_favorite_unactive);
        }
    }

    
    /* renamed from: lambda$onBindViewHolder$1$com-exorium-plant_recognition-adapter-HistoryInsectAdapter  reason: not valid java name */
    public  void m953x61d66a36(Insect insect, View view) {
        this.onInsectHistoryListener.onItemHistoryDelete(insect);
    }

    
    /* renamed from: lambda$onBindViewHolder$2$com-exorium-plant_recognition-adapter-HistoryInsectAdapter  reason: not valid java name */
    public  void m954x67da3595(Insect insect, View view) {
        this.onInsectHistoryListener.onItemHistoryClick(insect);
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
