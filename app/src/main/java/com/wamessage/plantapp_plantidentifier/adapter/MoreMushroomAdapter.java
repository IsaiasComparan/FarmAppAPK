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


public class MoreMushroomAdapter extends RecyclerView.Adapter<MoreMushroomAdapter.MoreMushroomHolder> {
    private List<Mushroom> mushrooms;

    public MoreMushroomAdapter(List<Mushroom> mushrooms) {
        this.mushrooms = mushrooms;
    }

    @Override 
    public MoreMushroomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoreMushroomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_more_info_plant, parent, false));
    }

    @Override 
    public int getItemCount() {
        return this.mushrooms.size();
    }

    @Override 
    public void onBindViewHolder(MoreMushroomHolder holder, int position) {
        Mushroom mushroom = this.mushrooms.get(position);
        holder.namePlant.setText(mushroom.getName());
        holder.score.setText("%" + mushroom.getScore());
    }

    
    public class MoreMushroomHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView namePlant;
        public TextView score;

        public MoreMushroomHolder(View itemView) {
            super(itemView);
            this.namePlant = (TextView) itemView.findViewById(R.id.txtInfoPlant_Name);
            this.score = (TextView) itemView.findViewById(R.id.txtInfoPlant_Score);
            this.cardView = (CardView) itemView.findViewById(R.id.plantCard1);
        }
    }
}
