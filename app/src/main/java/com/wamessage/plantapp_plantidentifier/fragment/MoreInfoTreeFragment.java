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
import com.wamessage.plantapp_plantidentifier.adapter.MoreTreeAdapter;
import java.util.List;


public class MoreInfoTreeFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.MoreInfoTreeFragment";
    private View mView;
    private MoreTreeAdapter moreTreeAdapter;
    private RecyclerView recyclerViewPlant;

    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_more_info_tree, container, false);
        this.mView = inflate;
        this.recyclerViewPlant = (RecyclerView) inflate.findViewById(R.id.rcv_infoPlant);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MoreInfoTreeFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                MoreInfoTreeFragment.this.m1002xfb4a8b5b(view);
            }
        });
        toolbar.setTitle(R.string.tree_list);
        ((RelativeLayout) getActivity().findViewById(R.id.relativeLayout)).setBackgroundResource(R.drawable.lightbg);
        Bundle arguments = getArguments();
        if (arguments != null) {
            MoreTreeAdapter moreTreeAdapter = new MoreTreeAdapter((List) arguments.get("trees"));
            this.moreTreeAdapter = moreTreeAdapter;
            this.recyclerViewPlant.setAdapter(moreTreeAdapter);
            this.recyclerViewPlant.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-MoreInfoTreeFragment  reason: not valid java name */
    public  void m1002xfb4a8b5b(View view) {
        getFragmentManager().popBackStack();
    }
}
