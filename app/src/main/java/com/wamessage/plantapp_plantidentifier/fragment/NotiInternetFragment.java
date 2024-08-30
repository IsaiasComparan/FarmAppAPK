package com.wamessage.plantapp_plantidentifier.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.PlantRecognitionActivity;
import com.makeramen.roundedimageview.RoundedImageView;


public class NotiInternetFragment extends Fragment {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.NotiInternetFragment";
    private PlantRecognitionActivity plantRecognitionActivity;
    private RoundedImageView roundedImageView;
    View view;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_noti_internet, container, false);
        this.plantRecognitionActivity = (PlantRecognitionActivity) getActivity();
        this.roundedImageView = (RoundedImageView) this.view.findViewById(R.id.roundedImageView);
        Bundle arguments = getArguments();
        if (arguments != null) {
            byte[] byteArray = arguments.getByteArray("img");
            radiusImage(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
        }
        return this.view;
    }

    private void radiusImage(Bitmap image) {
        Glide.with(this).load(image).override(200, 300).apply((BaseRequestOptions<?>) new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(40))).centerCrop().into(this.roundedImageView);
    }
}
