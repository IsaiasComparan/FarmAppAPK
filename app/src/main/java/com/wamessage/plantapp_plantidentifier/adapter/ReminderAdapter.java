package com.wamessage.plantapp_plantidentifier.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnReminderListener;
import com.wamessage.plantapp_plantidentifier.models.Reminder;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;


public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MorePlantHolder> {
    private OnReminderListener onReminderListener;
    private List<Reminder> reminderList;

    public ReminderAdapter(List<Reminder> reminderList, OnReminderListener onReminderListener) {
        this.reminderList = reminderList;
        this.onReminderListener = onReminderListener;
    }

    @Override 
    public MorePlantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MorePlantHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false));
    }

    @Override 
    public int getItemCount() {
        List<Reminder> list = this.reminderList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override 
    public void onBindViewHolder(MorePlantHolder holder, int position) {
        final Reminder reminder = this.reminderList.get(position);
        holder.plant_name.setText(reminder.getPlantName());
        holder.alarm_time.setText(reminder.getAlarmTime().getHours() + ":" + reminder.getAlarmTime().getMinutes());
        holder.imageViewReminder.setImageBitmap(BitmapFactory.decodeByteArray(reminder.getImage(), 0, reminder.getImage().length));
        holder.imageViewUpdate.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.ReminderAdapter$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                ReminderAdapter.this.m968xfa0ece39(reminder, view);
            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.ReminderAdapter$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                ReminderAdapter.this.m969x2363237a(reminder, view);
            }
        });
    }

    
    /* renamed from: lambda$onBindViewHolder$0$com-exorium-plant_recognition-adapter-ReminderAdapter  reason: not valid java name */
    public  void m968xfa0ece39(Reminder reminder, View view) {
        this.onReminderListener.onReminderUpdate(reminder);
    }

    
    /* renamed from: lambda$onBindViewHolder$1$com-exorium-plant_recognition-adapter-ReminderAdapter  reason: not valid java name */
    public  void m969x2363237a(Reminder reminder, View view) {
        this.onReminderListener.onReminderDelete(reminder);
    }

    
    public class MorePlantHolder extends RecyclerView.ViewHolder {
        public TextView alarm_time;
        public ImageView imageViewDelete;
        public ImageView imageViewReminder;
        public ImageView imageViewUpdate;
        public TextView plant_name;

        public MorePlantHolder(View itemView) {
            super(itemView);
            this.plant_name = (TextView) itemView.findViewById(R.id.textViewPlantName);
            this.alarm_time = (TextView) itemView.findViewById(R.id.textViewAlarmTime);
            this.imageViewReminder = (ImageView) itemView.findViewById(R.id.imageViewReminder);
            this.imageViewUpdate = (ImageView) itemView.findViewById(R.id.imageViewUpdate);
            this.imageViewDelete = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
