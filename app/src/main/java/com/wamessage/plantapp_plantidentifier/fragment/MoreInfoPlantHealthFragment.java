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
import com.wamessage.plantapp_plantidentifier.adapter.MorePlantHealthAdapter;
import java.util.List;


public class MoreInfoPlantHealthFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.MoreInfoPlantHealthFragment";
    private View mView;
    private MorePlantHealthAdapter morePlantHealthAdapter;
    private RecyclerView recyclerView;

    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_more_info_plant_health, container, false);
        this.mView = inflate;
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.rcv_infoPlant);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MoreInfoPlantHealthFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                MoreInfoPlantHealthFragment.this.m1001x1b720c2a(view);
            }
        });
        toolbar.setTitle(R.string.plant_health_list);
        ((RelativeLayout) getActivity().findViewById(R.id.relativeLayout)).setBackgroundResource(R.drawable.lightbg);
        Bundle arguments = getArguments();
        if (arguments != null) {
            MorePlantHealthAdapter morePlantHealthAdapter = new MorePlantHealthAdapter((List) arguments.get("plantHealths"));
            this.morePlantHealthAdapter = morePlantHealthAdapter;
            this.recyclerView.setAdapter(morePlantHealthAdapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-MoreInfoPlantHealthFragment  reason: not valid java name */
    public  void m1001x1b720c2a(View view) {
        getFragmentManager().popBackStack();
    }
}
