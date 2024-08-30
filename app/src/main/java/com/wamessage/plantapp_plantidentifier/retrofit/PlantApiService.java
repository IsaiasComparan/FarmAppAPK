package com.wamessage.plantapp_plantidentifier.retrofit;

import com.wamessage.plantapp_plantidentifier.models.GetAllResult;
import com.wamessage.plantapp_plantidentifier.models.RecognitionResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface PlantApiService {
    @GET("/")
    Call<GetAllResult> getAllPlant();

    @POST("/predict")
    @Multipart
    Call<RecognitionResult> recognizePlant(@Part MultipartBody.Part image);
}
