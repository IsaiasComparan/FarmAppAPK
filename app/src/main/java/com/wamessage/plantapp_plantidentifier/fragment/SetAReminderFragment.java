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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.wamessage.plantapp_plantidentifier.BuildConfig;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.DayOfWeekAdapter;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.listeners.OnDayOfWeekListener;
import com.wamessage.plantapp_plantidentifier.models.MyDay;
import com.wamessage.plantapp_plantidentifier.models.Reminder;
import com.wamessage.plantapp_plantidentifier.utils.AlarmReceiver;
import com.wamessage.plantapp_plantidentifier.utils.ConnectionUtils;

import com.makeramen.roundedimageview.RoundedImageView;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class SetAReminderFragment extends Fragment implements OnDayOfWeekListener {
    public static final String TAG = "com.wamessage.plantapp_plantidentifier.fragment.SetAReminderFragment";
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
    String type;
    private Activity wateringReminderActivity;
    private final int REQUEST_CODE_UPLOAD_IMAGE = 1;
    private final int READ_CODE_MEDIA_IMAGES = 200;
    private final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 2;
    List<Integer> listIntDay = new ArrayList();
    boolean isExist = false;
    private int notificationId = 1;


    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_set_a_reminder, container, false);
        FragmentActivity activity = getActivity();
        this.wateringReminderActivity = activity;
        Toolbar toolbar = (Toolbar) ((Activity) Objects.requireNonNull(activity)).findViewById(R.id.toolbar_watering_reminder);
        if (toolbar != null) {
            toolbar.setTitle(this.wateringReminderActivity.getResources().getString(R.string.set_reminder));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.SetAReminderFragment$$ExternalSyntheticLambda0
                @Override 
                public final void onClick(View view) {
                    SetAReminderFragment.this.m1025x1819612b(view);
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
        setDayOfWeek();
        setEvents();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.type = arguments.getString("type", "plant");
            Log.e("TAG", "type: " + this.type);
        }

        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-SetAReminderFragment  reason: not valid java name */
    public  void m1025x1819612b(View view) {
        requireFragmentManager().popBackStack();
    }



    @Override 
    public void onStart() {
        super.onStart();

    }

    private void setDayOfWeek() {
        Calendar.getInstance();
        ArrayList<MyDay> arrayList = new ArrayList<>();
        this.dayOfWeekList = arrayList;
        arrayList.add(new MyDay(2, 2));
        this.dayOfWeekList.add(new MyDay(3, 3));
        this.dayOfWeekList.add(new MyDay(4, 4));
        this.dayOfWeekList.add(new MyDay(5, 5));
        this.dayOfWeekList.add(new MyDay(6, 6));
        this.dayOfWeekList.add(new MyDay(7, 7));
        this.dayOfWeekList.add(new MyDay(1, 1));
        DayOfWeekAdapter dayOfWeekAdapter = new DayOfWeekAdapter(getActivity(), this.dayOfWeekList, this);
        this.adapter = dayOfWeekAdapter;
        this.idGVDayOfWeek.setAdapter((ListAdapter) dayOfWeekAdapter);
    }

    private void setEvents() {
        this.textViewTime.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.SetAReminderFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                SetAReminderFragment.this.m1026x8bfa5aad(view);
            }
        });
        this.imgUpLoad.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.SetAReminderFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                SetAReminderFragment.this.m1027x466ffb2e(view);
            }
        });
        this.btnAdd_Reminder.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.SetAReminderFragment$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view) {
                SetAReminderFragment.this.m1028xe59baf(view);
            }
        });
    }

    
    /* renamed from: lambda$setEvents$1$com-exorium-plant_recognition-fragment-SetAReminderFragment  reason: not valid java name */
    public  void m1026x8bfa5aad(View view) {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this.wateringReminderActivity, new TimePickerDialog.OnTimeSetListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.SetAReminderFragment.2
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String valueOf = String.valueOf(minute);
                if (minute < 10) {
                    valueOf = "0" + minute;
                }
                SetAReminderFragment.this.hourSelected = hour;
                SetAReminderFragment.this.minuteSelected = minute;
                SetAReminderFragment.this.textViewTime.setText(hour + ":" + valueOf);
            }
        }, calendar.get(10), calendar.get(12), true).show();
    }

    
    /* renamed from: lambda$setEvents$2$com-exorium-plant_recognition-fragment-SetAReminderFragment  reason: not valid java name */
    public  void m1027x466ffb2e(View view) {

        getPermissionStorage();
    }

    
    /* renamed from: lambda$setEvents$3$com-exorium-plant_recognition-fragment-SetAReminderFragment  reason: not valid java name */
    public  void m1028xe59baf(View view) {
        String obj = this.edtPlant_Name.getText().toString();
        String obj2 = this.edtAlarm_Name.getText().toString();
        Log.e("TAG", "edtPlant_Name: " + this.edtPlant_Name.getText().toString());
        Log.e("TAG", "edtAlarm_Name: " + this.edtAlarm_Name.getText().toString());
        if (!isValid().booleanValue()) {
            Activity activity = this.wateringReminderActivity;
            Toast.makeText(activity, activity.getResources().getString(R.string.empty_field), Toast.LENGTH_SHORT).show();
            return;
        }
        Calendar calendar = Calendar.getInstance();
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
        AppDatabase.getInstance(getActivity()).reminderDao().insertReminder(new Reminder(obj, obj2, calendar.getTime(), this.listIntDay, Bitmap_To_Byte(this.imagebitmap), this.type));
        getFragmentManager().popBackStack();
    }

    private void gotoMoreListReminderFragment() {
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        ListReminderFragment listReminderFragment = new ListReminderFragment();
        listReminderFragment.setArguments(new Bundle());
        beginTransaction.replace(R.id.content_frame_watering_reminder, listReminderFragment);
        beginTransaction.addToBackStack(TAG);
        beginTransaction.commit();
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

    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null) {
            this.dialog.dismiss();
            try {
                this.imageuri = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.wateringReminderActivity.getContentResolver(), this.imageuri);
                this.imagebitmap = bitmap;
                this.imagebitmap = getResizedBitmap(bitmap, 100);
                Glide.with(this.wateringReminderActivity).load(this.imageuri).centerCrop().into(this.imgUpLoad);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        if (this.imageuri != null && !this.edtPlant_Name.getText().toString().equals("") && !this.edtAlarm_Name.getText().toString().equals("") && !this.textViewTime.getText().equals("")) {
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
}
