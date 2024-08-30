package com.wamessage.plantapp_plantidentifier.retrofit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static Retrofit instance;
    static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    static OkHttpClient.Builder okBuilder = new OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS).connectTimeout(100, TimeUnit.SECONDS).retryOnConnectionFailure(true).addInterceptor(loggingInterceptor);

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder().baseUrl("http://192.168.2.3:8080/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(okBuilder.build()).build();
        }
        return instance;
    }
}
