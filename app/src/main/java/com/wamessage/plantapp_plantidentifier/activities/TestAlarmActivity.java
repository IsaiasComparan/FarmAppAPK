package com.wamessage.plantapp_plantidentifier.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.core.app.NotificationCompat;
import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.utils.AlarmNhan;

import java.util.Calendar;


public class TestAlarmActivity extends AppSuperBase {
    private AlarmManager alarmManager;
    private Button btnDat;
    private Button btnDung;
    private PendingIntent pendingIntent;
    private TimePicker timePicker;
    private TextView txtTime;

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_alarm);
        this.timePicker = (TimePicker) findViewById(R.id.timePic);
        this.btnDat = (Button) findViewById(R.id.btnDatGio);
        this.btnDung = (Button) findViewById(R.id.btnDung);
        this.txtTime = (TextView) findViewById(R.id.txtTime1);
        this.btnDung.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.TestAlarmActivity$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view) {
                TestAlarmActivity.this.m942x5b8b2f86(view);
            }
        });
        this.btnDat.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.TestAlarmActivity$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                TestAlarmActivity.this.m943x618efae5(view);
            }
        });
    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-TestAlarmActivity  reason: not valid java name */
    public  void m942x5b8b2f86(View view) {
        this.pendingIntent.cancel();
    }

    
    /* renamed from: lambda$onCreate$1$com-exorium-plant_recognition-activities-TestAlarmActivity  reason: not valid java name */
    public  void m943x618efae5(View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(11, this.timePicker.getCurrentHour().intValue());
        calendar.set(12, this.timePicker.getCurrentMinute().intValue());
        int intValue = this.timePicker.getCurrentHour().intValue();
        int intValue2 = this.timePicker.getCurrentMinute().intValue();
        String valueOf = String.valueOf(intValue);
        String valueOf2 = String.valueOf(intValue2);
        if (intValue > 12) {
            valueOf = String.valueOf(intValue - 12);
        }
        if (intValue2 < 10) {
            valueOf2 = "0" + valueOf2;
        }
        this.txtTime.setText("Thoi gian hen" + valueOf + ":" + valueOf2);
        Intent intent = new Intent(this, AlarmNhan.class);
        intent.setAction("MyAction");
        intent.putExtra("time", valueOf + ":" + valueOf2);
        this.alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        this.pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        this.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), this.pendingIntent);
    }
}
