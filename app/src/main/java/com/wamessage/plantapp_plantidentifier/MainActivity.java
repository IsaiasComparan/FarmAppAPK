package com.wamessage.plantapp_plantidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.wamessage.plantapp_plantidentifier.activities.PlantRecognitionActivity;
import com.wamessage.plantapp_plantidentifier.models.GetAllResult;
import com.wamessage.plantapp_plantidentifier.retrofit.PlantApiService;
import com.wamessage.plantapp_plantidentifier.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppSuperBase {
    Button btn_getAllPlant;
    Button btn_gotoRecognition;
    PlantApiService plantApiService;
    TextView textViewResult;

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textViewResult = (TextView) findViewById(R.id.textViewResult);
        this.btn_getAllPlant = (Button) findViewById(R.id.btn_getAllPlant);
        this.btn_gotoRecognition = (Button) findViewById(R.id.btn_gotoRecognition);
        this.plantApiService = (PlantApiService) RetrofitClient.getInstance().create(PlantApiService.class);
        this.btn_getAllPlant.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) {
                MainActivity.this.plantApiService.getAllPlant().enqueue(new Callback<GetAllResult>() { 
                    @Override
                    public void onFailure(Call<GetAllResult> call, Throwable t) {
                    }

                    @Override
                    public void onResponse(Call<GetAllResult> call, Response<GetAllResult> response) {
                        if (response.isSuccessful()) {
                            GetAllResult body = response.body();
                            Log.e("TAG", "KEt qua " + body.getPayload().getRecognized().toString());
                            MainActivity.this.textViewResult.setText(body.getPayload().getRecognized().toString());
                        }
                    }
                });
            }
        });
        this.btn_gotoRecognition.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                MainActivity.this.m888lambda$onCreate$0$comexoriumplant_recognitionMainActivity(view);
            }
        });
    }


    public  void m888lambda$onCreate$0$comexoriumplant_recognitionMainActivity(View view) {
        startActivity(new Intent(this, PlantRecognitionActivity.class));
    }
}
