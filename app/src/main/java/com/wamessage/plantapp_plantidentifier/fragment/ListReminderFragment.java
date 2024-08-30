package com.wamessage.plantapp_plantidentifier.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.WateringReminderActivity;
import com.wamessage.plantapp_plantidentifier.adapter.ReminderAdapter;
import java.io.ByteArrayOutputStream;


public class ListReminderFragment extends Fragment {
    private AlarmManager alarmManager;
    private View mView;
    private PendingIntent pendingIntent;
    private RecyclerView recyclerViewPlant;
    private ReminderAdapter reminderAdapter;
    private WateringReminderActivity wateringReminderActivity;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_list_reminder, container, false);
        this.mView = inflate;
        return inflate;
    }

    public byte[] Bitmap_To_Byte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
