package com.wamessage.plantapp_plantidentifier.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.models.Tree;
import java.util.List;


public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.PlantViewHolder> {
    private List<Tree> trees;

    public TreeAdapter(List<Tree> trees) {
        this.trees = trees.subList(1, trees.size());
    }

    @Override 
    public PlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant, parent, false));
    }

    @Override 
    public void onBindViewHolder(PlantViewHolder holder, int position) {
        Tree tree = this.trees.get(position);
        holder.namePlant.setText(tree.getName());
        holder.score.setText("%" + tree.getScore());
    }

    @Override 
    public int getItemCount() {
        return this.trees.size();
    }


    public class PlantViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView namePlant;
        public TextView score;

        public PlantViewHolder(View itemView) {
            super(itemView);
            this.namePlant = (TextView) itemView.findViewById(R.id.txtItemPlant_Name);
            this.score = (TextView) itemView.findViewById(R.id.txtItemPlant_Score);
            this.cardView = (CardView) itemView.findViewById(R.id.plantCard);
        }
    }
}
