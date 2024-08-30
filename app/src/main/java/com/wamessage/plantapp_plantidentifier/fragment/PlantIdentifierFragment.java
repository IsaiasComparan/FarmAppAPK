package com.wamessage.plantapp_plantidentifier.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.wamessage.plantapp_plantidentifier.adapter.PlantAdapter;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.models.Plant;


import com.makeramen.roundedimageview.RoundedImageView;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PlantIdentifierFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.PlantIdentifierFragment";

    Button btn_Home;
    Button btn_more;
    ImageView imageViewFavorite;

    Plant plantBest;
    List<Plant> plants;
    private RoundedImageView roundedImageView;

    TextView txtPlant_Name;
    TextView txt_Score;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plant_identifier, container, false);
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantIdentifierFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view2) {
                PlantIdentifierFragment.this.m1017xca3ac690(view2);
            }
        });
        toolbar.setTitle(R.string.plant_recognition);
        ImageView imageView = (ImageView) requireActivity().findViewById(R.id.imageViewFavorite);
        this.imageViewFavorite = imageView;
        imageView.setVisibility(View.VISIBLE);
        ((RelativeLayout) requireActivity().findViewById(R.id.relativeLayout)).setBackgroundColor(getResources().getColor(R.color.white));
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.plantBest = (Plant) arguments.get("plantBest");
            this.plants = (List) arguments.get("plants");
            byte[] byteArray = arguments.getByteArray("img");
            this.txtPlant_Name.setText(this.plantBest.getName());
            this.txt_Score.setText("%" + this.plantBest.getScore());
            recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));
            recyclerView.setAdapter(new PlantAdapter((List) this.plants.stream().filter(new Predicate() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantIdentifierFragment$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return PlantIdentifierFragment.lambda$onViewCreated$1((Plant) obj);
                }
            }).limit(5L).collect(Collectors.toList())));
            if (byteArray != null) {
                radiusImage(BitmapFactory.decodeByteArray(byteArray, 0, ((byte[]) Objects.requireNonNull(byteArray)).length));
            } else {
                radiusImage(((BitmapDrawable) getResources().getDrawable(R.drawable.icon_holder)).getBitmap());
            }
        }
        this.btn_Home.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantIdentifierFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view2) {
                PlantIdentifierFragment.this.m1018xc6fcce4e(view2);
            }
        });
        this.btn_more.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantIdentifierFragment$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view2) {
                PlantIdentifierFragment.this.m1019x455dd22d(view2);
            }
        });
        this.imageViewFavorite.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantIdentifierFragment$$ExternalSyntheticLambda4
            @Override 
            public final void onClick(View view2) {
                PlantIdentifierFragment.this.m1020xc3bed60c(view2);
            }
        });

    }

    
    /* renamed from: lambda$onViewCreated$0$com-exorium-plant_recognition-fragment-PlantIdentifierFragment  reason: not valid java name */
    public  void m1017xca3ac690(View view) {
        requireFragmentManager().popBackStack();
    }

    
    public static  boolean lambda$onViewCreated$1(Plant plant) {
        return plant.getScore().doubleValue() >= 0.1d;
    }

    
    /* renamed from: lambda$onViewCreated$2$com-exorium-plant_recognition-fragment-PlantIdentifierFragment  reason: not valid java name */
    public  void m1018xc6fcce4e(View view) {
        Intent intent = new Intent(requireActivity(), HomeActivity.class);
        intent.putExtra("is_GoHome", true);
        requireActivity().setResult(-1, intent);
        requireActivity().finish();
    }

    
    /* renamed from: lambda$onViewCreated$3$com-exorium-plant_recognition-fragment-PlantIdentifierFragment  reason: not valid java name */
    public  void m1019x455dd22d(View view) {
        this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_unactive);
        this.imageViewFavorite.setVisibility(View.INVISIBLE);
        gotoMoreFragment();
    }

    
    /* renamed from: lambda$onViewCreated$4$com-exorium-plant_recognition-fragment-PlantIdentifierFragment  reason: not valid java name */
    public  void m1020xc3bed60c(View view) {
        handleFavorite();
    }



    private void gotoMoreFragment() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        MoreInfoPlantFragment moreInfoPlantFragment = new MoreInfoPlantFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("plants", (Serializable) this.plants);
        moreInfoPlantFragment.setArguments(bundle);
        beginTransaction.replace(R.id.content_frame, moreInfoPlantFragment);
        beginTransaction.addToBackStack(MoreInfoPlantFragment.TAG);
        beginTransaction.commit();
    }

    private void radiusImage(Bitmap image) {
        Glide.with(this).load(image).apply((BaseRequestOptions<?>) new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(40))).centerCrop().into(this.roundedImageView);
    }

    private void handleFavorite() {
        if (!this.plantBest.getFavorited().booleanValue()) {
            this.plantBest.setFavorited(true);
            AppDatabase.getInstance(requireActivity()).plantDao().updatePlant(this.plantBest);
            this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_active);
            return;
        }
        this.plantBest.setFavorited(false);
        AppDatabase.getInstance(requireActivity()).plantDao().updatePlant(this.plantBest);
        this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_unactive);
    }

    @Override 
    public void onResume() {

        super.onResume();
        if (!this.plantBest.getFavorited().booleanValue()) {
            this.plantBest.setFavorited(false);
            AppDatabase.getInstance(requireActivity()).plantDao().updatePlant(this.plantBest);
            this.imageViewFavorite.setImageResource(R.drawable.ic_favorite_unactive);
            return;
        }
        this.plantBest.setFavorited(true);
        AppDatabase.getInstance(requireActivity()).plantDao().updatePlant(this.plantBest);
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
