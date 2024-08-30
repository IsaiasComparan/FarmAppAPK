package com.wamessage.plantapp_plantidentifier.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.HomeActivity;
import com.wamessage.plantapp_plantidentifier.adapter.PlantHealthAdapter;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.models.PlantHealth;

import com.facebook.shimmer.ShimmerFrameLayout;

import com.makeramen.roundedimageview.RoundedImageView;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PlantHealthFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.PlantHealthFragment";

    Button btn_Home;
    Button btn_more;
    ImageView imageViewFavorite;
    private boolean isInitializedBannerAd = false;
    PlantHealth plantHealthBest;
    List<PlantHealth> plantHealths;
    private RoundedImageView roundedImageView;

    TextView txtPlant_Name;
    TextView txt_Score;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plant_health, container, false);
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       this.roundedImageView = (RoundedImageView) view.findViewById(R.id.roundedImageView);
        this.txtPlant_Name = (TextView) view.findViewById(R.id.txtPlant_Name);
        this.txt_Score = (TextView) view.findViewById(R.id.txt_Score);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv1);
        this.btn_Home = (Button) view.findViewById(R.id.btn_Home);
        this.btn_more = (Button) view.findViewById(R.id.btn_more);
        Toolbar toolbar = (Toolbar) requireActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view2) {
                PlantHealthFragment.this.m1010x734a8cc3(view2);
            }
        });
        toolbar.setTitle(R.string.plant_health);
        ImageView imageView = (ImageView) requireActivity().findViewById(R.id.imageViewFavorite);
        this.imageViewFavorite = imageView;
        imageView.setVisibility(View.VISIBLE);
        ((RelativeLayout) requireActivity().findViewById(R.id.relativeLayout)).setBackgroundColor(getResources().getColor(R.color.white));
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.plantHealthBest = (PlantHealth) arguments.get("plantHealthBest");
            this.plantHealths = (List) arguments.get("plantHealths");
            byte[] byteArray = arguments.getByteArray("img");
            this.txtPlant_Name.setText(this.plantHealths.get(0).getName());
            this.txt_Score.setText("%" + this.plantHealths.get(0).getScore());
            recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
            recyclerView.setAdapter(new PlantHealthAdapter((List) this.plantHealths.stream().filter(new Predicate() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthFragment$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return PlantHealthFragment.lambda$onViewCreated$1((PlantHealth) obj);
                }
            }).limit(5L).collect(Collectors.toList())));
            radiusImage(BitmapFactory.decodeByteArray(byteArray, 0, ((byte[]) Objects.requireNonNull(byteArray)).length));
        }
        this.btn_Home.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view2) {
                PlantHealthFragment.this.m1011x7f522381(view2);
            }
        });
        this.btn_more.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthFragment$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view2) {
                PlantHealthFragment.this.m1012x8555eee0(view2);
            }
        });
        this.imageViewFavorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthFragment$$ExternalSyntheticLambda4
            @Override 
            public final void onClick(View view2) {
                PlantHealthFragment.this.m1013x8b59ba3f(view2);
            }
        });

    }

    
    /* renamed from: lambda$onViewCreated$0$com-exorium-plant_recognition-fragment-PlantHealthFragment  reason: not valid java name */
    public  void m1010x734a8cc3(View view) {
        requireFragmentManager().popBackStack();
    }

    
    public static  boolean lambda$onViewCreated$1(PlantHealth plantHealth) {
        return plantHealth.getScore().doubleValue() >= 0.1d;
    }

    
    /* renamed from: lambda$onViewCreated$2$com-exorium-plant_recognition-fragment-PlantHealthFragment  reason: not valid java name */
    public  void m1011x7f522381(View view) {
        Intent intent = new Intent(requireActivity(), HomeActivity.class);
        intent.putExtra("is_GoHome", true);
        requireActivity().setResult(-1, intent);
        requireActivity().finish();
    }

    
    /* renamed from: lambda$onViewCreated$3$com-exorium-plant_recognition-fragment-PlantHealthFragment  reason: not valid java name */
    public  void m1012x8555eee0(View view) {
        gotoMoreFragment();
    }

    
    /* renamed from: lambda$onViewCreated$4$com-exorium-plant_recognition-fragment-PlantHealthFragment  reason: not valid java name */
    public  void m1013x8b59ba3f(View view) {
        handleFavorite();
    }



    private void gotoMoreFragment() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        MoreInfoPlantHealthFragment moreInfoPlantHealthFragment = new MoreInfoPlantHealthFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("plantHealths", (Serializable) this.plantHealths);
        moreInfoPlantHealthFragment.setArguments(bundle);
        beginTransaction.replace(R.id.content_frame, moreInfoPlantHealthFragment);
        beginTransaction.addToBackStack(MoreInfoPlantHealthFragment.TAG);
        beginTransaction.commit();
    }

    private void radiusImage(Bitmap image) {
        Glide.with(this).load(image).apply((BaseRequestOptions<?>) new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(40))).centerCrop().into(this.roundedImageView);
    }

    private void handleFavorite() {
        if (!this.plantHealthBest.getFavorited().booleanValue()) {
            this.plantHealthBest.setFavorited(true);
            AppDatabase.getInstance(requireActivity()).plantHealthDao().updatePlantHealth(this.plantHealthBest);
            this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_active);
            return;
        }
        this.plantHealthBest.setFavorited(false);
        AppDatabase.getInstance(requireActivity()).plantHealthDao().updatePlantHealth(this.plantHealthBest);
        this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_unactive);
    }

    @Override 
    public void onResume() {

        super.onResume();
        if (!this.plantHealthBest.getFavorited().booleanValue()) {
            this.plantHealthBest.setFavorited(false);
            AppDatabase.getInstance(requireActivity()).plantHealthDao().updatePlantHealth(this.plantHealthBest);
            this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_unactive);
            return;
        }
        this.plantHealthBest.setFavorited(true);
        AppDatabase.getInstance(requireActivity()).plantHealthDao().updatePlantHealth(this.plantHealthBest);
        this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_active);
    }

    @Override 
    public void onStart() {
        super.onStart();

    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
        this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_unactive);
        this.imageViewFavorite.setVisibility(View.INVISIBLE);
    }
}
