package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.models.Language;
import java.util.List;


public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {
    private final List<Language> languages;
    private final OnLanguageSelectedListener onLanguageSelectedListener;
    private int selectedLang;


    public interface OnLanguageSelectedListener {
        void onLanguageSelected(int position);
    }

    public int getSelectedLang() {
        return this.selectedLang;
    }

    public LanguageAdapter(List<Language> languages, int selectedLang, OnLanguageSelectedListener itemClickListener) {
        this.languages = languages;
        this.selectedLang = selectedLang;
        this.onLanguageSelectedListener = itemClickListener;
    }

    @Override 
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LanguageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_language_radio, parent, false));
    }

    @Override 
    public void onBindViewHolder(final LanguageViewHolder holder, final int position) {
        Language language = this.languages.get(position);
        holder.name.setText(language.getName());
        holder.img.setImageResource(language.getImg());
        holder.cardView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.LanguageAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                LanguageAdapter.this.m967x4e110c93(holder, position, view);
            }
        });
        if (this.selectedLang == position) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_card_selected));
            holder.radioButton.setChecked(true);
            holder.name.setTextColor(Color.parseColor("#54A871"));
            return;
        }
        holder.linearLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.bg_card_unselected));
        holder.radioButton.setChecked(false);
        holder.name.setTextColor(Color.parseColor("#888888"));
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-LanguageAdapter  reason: not valid java name */
    public  void m967x4e110c93(LanguageViewHolder languageViewHolder, int i, View view) {
        setSelectedLang(languageViewHolder.getAdapterPosition());
        OnLanguageSelectedListener onLanguageSelectedListener = this.onLanguageSelectedListener;
        if (onLanguageSelectedListener != null) {
            onLanguageSelectedListener.onLanguageSelected(i);
        }
    }

    private void setSelectedLang(int adapterPosition) {
        this.selectedLang = adapterPosition;
        notifyDataSetChanged();
    }

    @Override 
    public int getItemCount() {
        return this.languages.size();
    }


    public class LanguageViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView img;
        public LinearLayout linearLayout;
        public TextView name;
        public RadioButton radioButton;

        public LanguageViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.lang_name);
            this.img = (ImageView) itemView.findViewById(R.id.img);
            this.linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_language);
            this.cardView = (CardView) itemView.findViewById(R.id.lang_card);
            this.radioButton = (RadioButton) itemView.findViewById(R.id.radio);
        }
    }
}
