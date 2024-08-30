package com.wamessage.plantapp_plantidentifier.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.MorePlantAdapter;
import java.util.List;


public class MoreInfoPlantFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.MoreInfoPlantFragment";
    private View mView;
    private MorePlantAdapter morePlantAdapter;
    private RecyclerView recyclerViewPlant;

    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_more_info_plant, container, false);
        this.mView = inflate;
        this.recyclerViewPlant = (RecyclerView) inflate.findViewById(R.id.rcv_infoPlant);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MoreInfoPlantFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                MoreInfoPlantFragment.this.m1000xb27202e(view);
            }
        });
        toolbar.setTitle(R.string.plant_list);
        ((RelativeLayout) getActivity().findViewById(R.id.relativeLayout)).setBackgroundResource(R.drawable.lightbg);
        Bundle arguments = getArguments();
        if (arguments != null) {
            MorePlantAdapter morePlantAdapter = new MorePlantAdapter((List) arguments.get("plants"));
            this.morePlantAdapter = morePlantAdapter;
            this.recyclerViewPlant.setAdapter(morePlantAdapter);
            this.recyclerViewPlant.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-MoreInfoPlantFragment  reason: not valid java name */
    public  void m1000xb27202e(View view) {
        getFragmentManager().popBackStack();
    }
}
