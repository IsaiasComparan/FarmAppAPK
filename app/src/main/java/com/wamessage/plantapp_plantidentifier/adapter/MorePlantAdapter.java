package com.wamessage.plantapp_plantidentifier.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.models.Plant;
import java.util.List;


public class MorePlantAdapter extends RecyclerView.Adapter<MorePlantAdapter.MorePlantHolder> {
    private List<Plant> plants;

    public MorePlantAdapter(List<Plant> plants) {
        this.plants = plants;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_info_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        return this.plants.size();
    }

    @Override 
    public void onBindViewHolder(MorePlantHolder holder, int position) {
        Plant plant = this.plants.get(position);
        holder.namePlant.setText(plant.getName());
        holder.score.setText("%" + plant.getScore());
    }

    
    public class MorePlantHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView namePlant;
        public TextView score;

        public MorePlantHolder(View itemView) {
            super(itemView);
            this.namePlant = (TextView) itemView.findViewById(R.id.txtInfoPlant_Name);
            this.score = (TextView) itemView.findViewById(R.id.txtInfoPlant_Score);
            this.cardView = (CardView) itemView.findViewById(R.id.plantCard1);
        }
    }
}
