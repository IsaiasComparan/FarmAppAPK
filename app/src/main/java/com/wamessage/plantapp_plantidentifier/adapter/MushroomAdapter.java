package com.wamessage.plantapp_plantidentifier.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.models.Mushroom;
import java.util.List;


public class MushroomAdapter extends RecyclerView.Adapter<MushroomAdapter.MushroomViewHolder> {
    private List<Mushroom> mushrooms;

    public MushroomAdapter(List<Mushroom> mushrooms) {
        this.mushrooms = mushrooms.subList(1, mushrooms.size());
    }

    @Override 
    public MushroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MushroomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false));
    }

    @Override 
    public void onBindViewHolder(MushroomViewHolder holder, int position) {
        Mushroom mushroom = this.mushrooms.get(position);
        holder.namePlant.setText(mushroom.getName());
        holder.score.setText("%" + mushroom.getScore());
    }

    @Override 
    public int getItemCount() {
        return this.mushrooms.size();
    }

    
    public class MushroomViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView namePlant;
        public TextView score;

        public MushroomViewHolder(View itemView) {
            super(itemView);
            this.namePlant = (TextView) itemView.findViewById(R.id.txtItemPlant_Name);
            this.score = (TextView) itemView.findViewById(R.id.txtItemPlant_Score);
            this.cardView = (CardView) itemView.findViewById(R.id.plantCard);
        }
    }
}
