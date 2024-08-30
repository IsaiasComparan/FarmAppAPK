package com.wamessage.plantapp_plantidentifier.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.SettingActivity;


public class HomeReminderFragment extends Fragment {
    View rootView;

    
    public static  void lambda$initEvent$0(View view) {
    }

    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToWateringReminderFragment();
    }

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home_reminder, container, false);
        this.rootView = inflate;
        return inflate;
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

     }


    public  void m989x51201e7c(View view) {
        startActivity(new Intent(requireContext(), SettingActivity.class));
    }

    public void goToWateringReminderFragment() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        WateringReminderFragment wateringReminderFragment = new WateringReminderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", "plant");
        wateringReminderFragment.setArguments(bundle);
        beginTransaction.replace(R.id.fragmentContent, wateringReminderFragment);
        beginTransaction.commit();
    }
}
