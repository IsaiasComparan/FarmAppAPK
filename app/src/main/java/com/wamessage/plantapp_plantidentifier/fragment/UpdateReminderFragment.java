package com.wamessage.plantapp_plantidentifier.fragment;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.DayOfWeekAdapter;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.listeners.OnDayOfWeekListener;
import com.wamessage.plantapp_plantidentifier.models.MyDay;
import com.wamessage.plantapp_plantidentifier.models.PendingIntentEntity;
import com.wamessage.plantapp_plantidentifier.models.Reminder;
import com.wamessage.plantapp_plantidentifier.utils.AlarmReceiver;

import com.makeramen.roundedimageview.RoundedImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class UpdateReminderFragment extends Fragment implements OnDayOfWeekListener {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.UpdateReminderFragment";
    private DayOfWeekAdapter adapter;
    private AlarmManager alarmManager;
    private AppDatabase appDatabase;
    private AppCompatButton btnAdd_Reminder;
    private ArrayList<MyDay> dayOfWeekList;
    private Dialog dialog;
    private EditText edtAlarm_Name;
    private EditText edtPlant_Name;
    private int hourSelected;
    private GridView idGVDayOfWeek;
    private Bitmap imagebitmap;
    private Uri imageuri;
    private RoundedImageView imgUpLoad;
    private View mView;
    private int minuteSelected;
    private PendingIntent pendingIntent;
    private Reminder reminder;
    private TextView textViewTime;
    private TimePicker timePicker;
    private Activity wateringReminderActivity;
    private final int REQUEST_CODE_UPLOAD_IMAGE = 1;
    private final int READ_CODE_MEDIA_IMAGES = 200;
    private final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 2;
    List<Integer> listIntDay = new ArrayList();
    boolean isExist = false;
    private int notificationId = 1;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_update_reminder, container, false);
        FragmentActivity activity = getActivity();
        this.wateringReminderActivity = activity;
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar_watering_reminder);
        if (toolbar != null) {
            toolbar.setTitle(this.wateringReminderActivity.getResources().getString(R.string.update_reminder));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.UpdateReminderFragment$$ExternalSyntheticLambda0
                @Override 
                public final void onClick(View view) {
                    UpdateReminderFragment.this.m1037xecbbdb55(view);
                }
            });
        }
        this.imgUpLoad = (RoundedImageView) this.mView.findViewById(R.id.imgUpLoad);
        this.edtPlant_Name = (EditText) this.mView.findViewById(R.id.edtPlant_Name);
        this.edtAlarm_Name = (EditText) this.mView.findViewById(R.id.edtAlarm_Name);
        this.textViewTime = (TextView) this.mView.findViewById(R.id.textViewTime);
        this.idGVDayOfWeek = (GridView) this.mView.findViewById(R.id.idGVDayOfWeek);
        this.btnAdd_Reminder = (AppCompatButton) this.mView.findViewById(R.id.btnAdd_Reminder);
        this.dialog = new Dialog(this.wateringReminderActivity);
        initData();
        setDayOfWeek();
        setEvents();
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-UpdateReminderFragment  reason: not valid java name */
    public  void m1037xecbbdb55(View view) {
        getFragmentManager().popBackStack();
    }

    private void initData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.reminder = (Reminder) arguments.get(NotificationCompat.CATEGORY_REMINDER);
            Calendar.getInstance().setTime(this.reminder.getAlarmTime());
            this.imagebitmap = BitmapFactory.decodeByteArray(this.reminder.getImage(), 0, this.reminder.getImage().length);
            Glide.with(getActivity()).load(this.imagebitmap).centerCrop().into(this.imgUpLoad);
            this.edtPlant_Name.setText(this.reminder.getPlantName());
            this.edtAlarm_Name.setText(this.reminder.getAlarmName());
            this.hourSelected = this.reminder.getAlarmTime().getHours();
            this.minuteSelected = this.reminder.getAlarmTime().getMinutes();
            this.textViewTime.setText((this.reminder.getAlarmTime().getHours() + "") + ":" + (this.reminder.getAlarmTime().getMinutes() < 10 ? new StringBuilder("0").append(this.reminder.getAlarmTime().getMinutes()) : new StringBuilder().append(this.reminder.getAlarmTime().getMinutes()).append("")).toString());
        }
    }

    private void setDayOfWeek() {
        ArrayList<MyDay> arrayList = new ArrayList<>();
        this.dayOfWeekList = arrayList;
        arrayList.add(new MyDay(2, 2));
        this.dayOfWeekList.add(new MyDay(3, 3));
        this.dayOfWeekList.add(new MyDay(4, 4));
        this.dayOfWeekList.add(new MyDay(5, 5));
        this.dayOfWeekList.add(new MyDay(6, 6));
        this.dayOfWeekList.add(new MyDay(7, 7));
        this.dayOfWeekList.add(new MyDay(1, 1));
        for (int i = 0; i < this.reminder.getDayOfWeeks().size(); i++) {
            Log.e("TAG", "reminder.get: " + this.reminder.getDayOfWeeks().get(i));
        }
        for (int i2 = 0; i2 < this.dayOfWeekList.size(); i2++) {
            for (int i3 = 0; i3 < this.reminder.getDayOfWeeks().size(); i3++) {
                if (this.dayOfWeekList.get(i2).getNamedDay() == this.reminder.getDayOfWeeks().get(i3).intValue()) {
                    this.dayOfWeekList.get(i2).setSelected(true);
                }
            }
        }
        for (int i4 = 0; i4 < this.dayOfWeekList.size(); i4++) {
            if (this.dayOfWeekList.get(i4).isSelected()) {
                Log.e("TAG", "dayOfWeekList.get: " + this.dayOfWeekList.get(i4));
            }
        }
        DayOfWeekAdapter dayOfWeekAdapter = new DayOfWeekAdapter(getActivity(), this.dayOfWeekList, this);
        this.adapter = dayOfWeekAdapter;
        this.idGVDayOfWeek.setAdapter((ListAdapter) dayOfWeekAdapter);
    }

    private void setEvents() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.reminder.getAlarmTime());
        this.textViewTime.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.UpdateReminderFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                UpdateReminderFragment.this.m1038xec447c57(calendar, view);
            }
        });
        this.imgUpLoad.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.UpdateReminderFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                UpdateReminderFragment.this.m1039xdfd40098(view);
            }
        });
        this.btnAdd_Reminder.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.UpdateReminderFragment$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view) {
                UpdateReminderFragment.this.m1040xd36384d9(calendar, view);
            }
        });
    }

    
    /* renamed from: lambda$setEvents$1$com-exorium-plant_recognition-fragment-UpdateReminderFragment  reason: not valid java name */
    public  void m1038xec447c57(Calendar calendar, View view) {
        new TimePickerDialog(this.wateringReminderActivity, new TimePickerDialog.OnTimeSetListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.UpdateReminderFragment.1
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String valueOf = String.valueOf(minute);
                if (minute < 10) {
                    valueOf = "0" + minute;
                }
                UpdateReminderFragment.this.hourSelected = hour;
                UpdateReminderFragment.this.minuteSelected = minute;
                UpdateReminderFragment.this.textViewTime.setText(hour + ":" + valueOf);
            }
        }, calendar.get(10), calendar.get(12), true).show();
    }

    
    /* renamed from: lambda$setEvents$2$com-exorium-plant_recognition-fragment-UpdateReminderFragment  reason: not valid java name */
    public  void m1039xdfd40098(View view) {
        getPermissionStorage();
    }

    
    /* renamed from: lambda$setEvents$3$com-exorium-plant_recognition-fragment-UpdateReminderFragment  reason: not valid java name */
    public  void m1040xd36384d9(Calendar calendar, View view) {
        String obj = this.edtPlant_Name.getText().toString();
        String obj2 = this.edtAlarm_Name.getText().toString();
        Log.e("TAG", "edtPlant_Name: " + this.edtPlant_Name.getText().toString());
        Log.e("TAG", "edtAlarm_Name: " + this.edtAlarm_Name.getText().toString());
        if (!isValid().booleanValue()) {
            Activity activity = this.wateringReminderActivity;
            Toast.makeText(activity, activity.getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            return;
        }
        calendar.set(11, this.hourSelected);
        calendar.set(12, this.minuteSelected);
        calendar.set(13, 0);
        Intent intent = new Intent(this.wateringReminderActivity, AlarmReceiver.class);
        intent.setAction("MyAction");
        intent.putExtra("time", this.hourSelected + ":" + this.minuteSelected);
        intent.putExtra("plant_name", obj);
        intent.putExtra("alarm_Name", obj2);
        this.alarmManager = (AlarmManager) this.wateringReminderActivity.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= 33) {
            this.pendingIntent = PendingIntent.getBroadcast(this.wateringReminderActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else if (Build.VERSION.SDK_INT >= 31) {
            this.pendingIntent = PendingIntent.getBroadcast(this.wateringReminderActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        } else {
            this.pendingIntent = PendingIntent.getBroadcast(this.wateringReminderActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        for (int i = 0; i < this.dayOfWeekList.size(); i++) {
            if (this.dayOfWeekList.get(i).isSelected()) {
                this.listIntDay.add(Integer.valueOf(this.dayOfWeekList.get(i).getNamedDay()));
                if (this.dayOfWeekList.get(i).getNamedDay() == calendar.get(7)) {
                    this.isExist = true;
                }
            }
        }
        if (this.isExist) {
            this.alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), this.pendingIntent);
        }
        new PendingIntentEntity(this.pendingIntent);
        this.reminder.setPlantName(obj);
        this.reminder.setAlarmName(obj2);
        this.reminder.setAlarmTime(calendar.getTime());
        this.reminder.setDayOfWeeks(this.listIntDay);
        this.reminder.setImage(Bitmap_To_Byte(this.imagebitmap));
        AppDatabase.getInstance(getActivity()).reminderDao().updateReminder(this.reminder);
        getFragmentManager().popBackStack();
    }

    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null) {
            this.imgUpLoad.setRotation(0.0f);
            this.imageuri = data.getData();
            try {
                this.dialog.dismiss();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.wateringReminderActivity.getContentResolver(), this.imageuri);
                this.imagebitmap = bitmap;
                this.imagebitmap = getResizedBitmap(bitmap, 100);
                Glide.with(getActivity()).load(this.imageuri).centerCrop().into(this.imgUpLoad);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    @Override 
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 || requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                openStorage2();
            } else {
                Toast.makeText(this.wateringReminderActivity, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public byte[] Bitmap_To_Byte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // com.wamessage.plantapp_plantidentifier.listeners.OnDayOfWeekListener
    public void onDayOfWeekClick(MyDay myDay) {
        this.dayOfWeekList = this.adapter.getDayofWeekList();
    }

    Boolean isValid() {
        Boolean bool;
        if (this.imagebitmap != null && !this.edtPlant_Name.getText().toString().equals("") && !this.edtAlarm_Name.getText().toString().equals("") && !this.textViewTime.getText().equals("")) {
            bool = true;
        } else {
            bool = false;
        }
        Boolean bool2 = false;
        for (int i = 0; i < this.dayOfWeekList.size(); i++) {
            if (this.dayOfWeekList.get(i).isSelected()) {
                bool2 = true;
            }
        }
        if (bool.booleanValue() && bool2.booleanValue()) {
            return true;
        }
        return false;
    }

    public void getPermissionStorage() {
        if (Build.VERSION.SDK_INT > 32) {
            if (this.wateringReminderActivity.checkSelfPermission("android.permission.READ_MEDIA_IMAGES") != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{"android.permission.READ_MEDIA_IMAGES"}, 200);
            } else {
                openStorage13();
            }
        } else if (this.wateringReminderActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2);
        } else {
            openStorage2();
        }
    }

    public void openStorage2() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    public void openStorage13() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }
}
