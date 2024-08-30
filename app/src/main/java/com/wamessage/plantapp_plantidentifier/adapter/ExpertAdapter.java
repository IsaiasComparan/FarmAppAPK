package com.wamessage.plantapp_plantidentifier.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.Botanic_ExpertActivity;
import com.wamessage.plantapp_plantidentifier.models.Expert;
import java.util.List;


public class ExpertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Botanic_ExpertActivity activity;
    List<Expert> expertList;
    final int VIEW_EXPERT = 0;
    final int VIEW_ADS = 1;

    public ExpertAdapter(Botanic_ExpertActivity activity, List<Expert> expertList) {
        this.activity = activity;
        this.expertList = expertList;
    }

    @Override 
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expert, parent, false));
    }

    @Override 
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Expert expert = this.expertList.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.textViewTitle.setText(expert.getTitle());
        myViewHolder.textViewContent.setText(expert.getContent());
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.ExpertAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                ExpertAdapter.this.m951x69630081(expert, position, view);
            }
        });
        Boolean expanded = expert.getExpanded();
        myViewHolder.expanded_layout.setVisibility(expanded.booleanValue() ? View.VISIBLE : View.GONE);
        myViewHolder.imageViewArrow.setImageResource(expanded.booleanValue() ? R.drawable.ic_arrow_up : R.drawable.ic_arrow_down);
        return;    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-ExpertAdapter  reason: not valid java name */
    public  void m951x69630081(Expert expert, int i, View view) {
        expert.setExpanded(Boolean.valueOf(!expert.getExpanded().booleanValue()));
        notifyItemChanged(i);
    }

    @Override 
    public int getItemCount() {
        return this.expertList.size();
    }

    public void notifyListeners() {
        notifyDataSetChanged();
    }

    @Override 
    public int getItemViewType(int position) {
        return this.expertList.get(position) == null ? 1 : 0;
    }

    
    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout expanded_layout;
        ImageView imageViewArrow;
        LinearLayout linearLayout;
        TextView textViewContent;
        TextView textViewTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
            this.expanded_layout = (RelativeLayout) itemView.findViewById(R.id.expanded_layout);
            this.imageViewArrow = (ImageView) itemView.findViewById(R.id.imageViewArrow);
        }
    }

    
    public static class AdsViewHolder extends RecyclerView.ViewHolder {
        private AdsViewHolder(View view) {
            super(view);
        }
    }
}
