package com.wamessage.plantapp_plantidentifier.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.ReminderAdapter;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.listeners.OnReminderListener;
import com.wamessage.plantapp_plantidentifier.models.Reminder;


import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;


public class WateringReminderFragment extends Fragment implements OnReminderListener {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.WateringReminderFragment";
    private ReminderAdapter adapter;


    private LinearLayout layoutNoRemind;
    private RecyclerView recyclerView;

    String type;

    private final List<Reminder> reminderList = new ArrayList();

    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_watering_reminder, container, false);
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialToolbar materialToolbar = (MaterialToolbar) requireActivity().findViewById(R.id.toolbar_watering_reminder);
        if (materialToolbar != null) {
            materialToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.WateringReminderFragment$$ExternalSyntheticLambda0
                @Override 
                public final void onClick(View view2) {
                    WateringReminderFragment.this.m1042x97776541(view2);
                }
            });
        }
        this.layoutNoRemind = (LinearLayout) view.findViewById(R.id.layoutNoRemind);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewReminder);

        ((Button) view.findViewById(R.id.btnSet_A_Reminder)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.WateringReminderFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view2) {
                WateringReminderFragment.this.m1043xe536dd42(view2);
            }
        });
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.type = arguments.getString("type", "plant");
            Log.e("TAG", "type: " + this.type);
        }
        setRecyclerView();
        initData();

    }

    
    /* renamed from: lambda$onViewCreated$0$com-exorium-plant_recognition-fragment-WateringReminderFragment  reason: not valid java name */
    public  void m1042x97776541(View view) {
        requireActivity().finish();
    }

    
    /* renamed from: lambda$onViewCreated$1$com-exorium-plant_recognition-fragment-WateringReminderFragment  reason: not valid java name */
    public  void m1043xe536dd42(View view) {
        openSetAReminderFragment();
    }

    private void initData() {
        this.reminderList.clear();
        this.reminderList.addAll(AppDatabase.getInstance(requireActivity()).reminderDao().getReminders(this.type));
        if (this.reminderList.size() != 0) {
            this.layoutNoRemind.setVisibility(View.GONE);
            this.recyclerView.setVisibility(View.VISIBLE);
            this.adapter.notifyDataSetChanged();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.recyclerView.getLayoutParams();
            marginLayoutParams.topMargin = 80;
            this.recyclerView.setLayoutParams(marginLayoutParams);
            if (this.reminderList.size() > 1) {

                marginLayoutParams.topMargin = 0;
                this.recyclerView.setLayoutParams(marginLayoutParams);
            }
        }
    }

    private void setRecyclerView() {
        this.adapter = new ReminderAdapter(this.reminderList, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        this.recyclerView.setAdapter(this.adapter);
    }

    @Override 
    public void onStart() {
        super.onStart();

    }

    @Override 
    public void onStop() {
        super.onStop();

    }



    private void openSetAReminderFragment() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        SetAReminderFragment setAReminderFragment = new SetAReminderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", this.type);
        setAReminderFragment.setArguments(bundle);
        beginTransaction.replace(R.id.content_frame_watering_reminder, setAReminderFragment);
        beginTransaction.addToBackStack(TAG);
        beginTransaction.commit();
    }

    @Override // com.wamessage.plantapp_plantidentifier.listeners.OnReminderListener
    public void onReminderUpdate(Reminder reminder) {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NotificationCompat.CATEGORY_REMINDER, reminder);
        UpdateReminderFragment updateReminderFragment = new UpdateReminderFragment();
        updateReminderFragment.setArguments(bundle);
        beginTransaction.replace(R.id.content_frame_watering_reminder, updateReminderFragment);
        beginTransaction.addToBackStack(TAG);
        beginTransaction.commit();
    }

    @Override // com.wamessage.plantapp_plantidentifier.listeners.OnReminderListener
    public void onReminderDelete(final Reminder reminder) {
        final Dialog dialog = new Dialog(requireActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.layout_dialog_delete);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);
        dialog.setCancelable(true);
        dialog.show();
        ((AppCompatButton) dialog.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.WateringReminderFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((AppCompatButton) dialog.findViewById(R.id.btnDelete)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.WateringReminderFragment$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view) {
                WateringReminderFragment.this.m1041x4c6a0976(reminder, dialog, view);
            }
        });
    }

    
    /* renamed from: lambda$onReminderDelete$3$com-exorium-plant_recognition-fragment-WateringReminderFragment  reason: not valid java name */
    public  void m1041x4c6a0976(Reminder reminder, Dialog dialog, View view) {
        handleDeleteItem(reminder);
        dialog.dismiss();
    }

    private void handleDeleteItem(Reminder reminder) {
        int indexOf = this.reminderList.indexOf(reminder);
        if (indexOf >= 0) {
            this.reminderList.remove(reminder);
            AppDatabase.getInstance(requireActivity()).reminderDao().deleteReminder(reminder);
            this.adapter.notifyItemRemoved(indexOf);
            if (this.reminderList.size() <= 1) {

                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.recyclerView.getLayoutParams();
                marginLayoutParams.topMargin = 80;
                this.recyclerView.setLayoutParams(marginLayoutParams);
            }
            if (this.reminderList.size() == 0) {
                this.layoutNoRemind.setVisibility(View.VISIBLE);
                this.recyclerView.setVisibility(View.GONE);
            }
        }
    }
}
