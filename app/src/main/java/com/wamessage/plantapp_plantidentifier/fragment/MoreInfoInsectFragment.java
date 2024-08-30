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
import com.wamessage.plantapp_plantidentifier.adapter.MoreInsectAdapter;
import java.util.List;


public class MoreInfoInsectFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.MoreInfoInsectFragment";
    private View mView;
    private MoreInsectAdapter moreInsectAdapter;
    private RecyclerView recyclerView;

    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_more_info_insect, container, false);
        this.mView = inflate;
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.rcv_infoPlant);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MoreInfoInsectFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                MoreInfoInsectFragment.this.m998x61994f65(view);
            }
        });
        toolbar.setTitle(R.string.insect_list);
        ((RelativeLayout) getActivity().findViewById(R.id.relativeLayout)).setBackgroundResource(R.drawable.lightbg);
        Bundle arguments = getArguments();
        if (arguments != null) {
            MoreInsectAdapter moreInsectAdapter = new MoreInsectAdapter((List) arguments.get("insects"));
            this.moreInsectAdapter = moreInsectAdapter;
            this.recyclerView.setAdapter(moreInsectAdapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-MoreInfoInsectFragment  reason: not valid java name */
    public  void m998x61994f65(View view) {
        getFragmentManager().popBackStack();
    }
}
