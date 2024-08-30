package com.wamessage.plantapp_plantidentifier.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import com.wamessage.plantapp_plantidentifier.AppSuperBase;
import com.wamessage.plantapp_plantidentifier.BuildConfig;
import com.wamessage.plantapp_plantidentifier.R;

import com.facebook.shimmer.ShimmerFrameLayout;

import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CameraActivity extends AppSuperBase {
    ImageButton backButton;

    ImageButton cameraCaptureButton;
    private ExecutorService cameraExecutor;
    ImageButton galleryButton;
    private ImageCapture imageCapture;
    private File outputDirectory;
    private PreviewView previewView;



    private final int REQUEST_CODE_CAMERA_PERMISSION = 100;
    private final int REQUEST_CODE_READ_MEDIA_IMAGES_PERMISSION = 200;
    private final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 300;
    final String TAG = "CameraActivity";
    final String FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS";
    private final int REQUEST_CODE_PICK_IMAGE = 10;
    private final int REQUEST_CODE_OPEN_GALLERY = 1;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$$ExternalSyntheticLambda0
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            CameraActivity.this.m897xf83086a((Uri) obj);
        }
    });

    
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        this.previewView = (PreviewView) findViewById(R.id.viewFinder);
        ImageButton imageButton = (ImageButton) findViewById(R.id.gallery_button);
        this.galleryButton = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                CameraActivity.this.m898x5853a1dc(view);
            }
        });
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.back_button);
        this.backButton = imageButton2;
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$$ExternalSyntheticLambda3
            @Override 
            public final void onClick(View view) {
                CameraActivity.this.m899x7de7aadd(view);
            }
        });
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.camera_capture_button);
        this.cameraCaptureButton = imageButton3;
        imageButton3.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$$ExternalSyntheticLambda4
            @Override 
            public final void onClick(View view) {
                CameraActivity.this.m900xa37bb3de(view);
            }
        });


        getPermissionCamera();
        this.outputDirectory = getOutputDirectory();
        this.cameraExecutor = Executors.newSingleThreadExecutor();
    }

    
    /* renamed from: lambda$onCreate$0$com-exorium-plant_recognition-activities-CameraActivity  reason: not valid java name */
    public  void m898x5853a1dc(View view) {
        getPermissionStorage();
    }

    
    /* renamed from: lambda$onCreate$1$com-exorium-plant_recognition-activities-CameraActivity  reason: not valid java name */
    public  void m899x7de7aadd(View view) {
        finish();
    }

    
    /* renamed from: lambda$onCreate$2$com-exorium-plant_recognition-activities-CameraActivity  reason: not valid java name */
    public  void m900xa37bb3de(View view) {
        takePhoto();
    }



    public void getPermissionCamera() {
        if (checkSelfPermission("android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
        } else {
            startCamera();
        }
    }

    public void getPermissionStorage() {
        if (Build.VERSION.SDK_INT > 32) {
            if (checkSelfPermission("android.permission.READ_MEDIA_IMAGES") != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{"android.permission.READ_MEDIA_IMAGES"}, 200);
            } else {
                openStorage();
            }
        } else if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 300);
        } else {
            openStorage();
        }
    }

    
    /* renamed from: lambda$new$3$com-exorium-plant_recognition-activities-CameraActivity  reason: not valid java name */
    public  void m897xf83086a(Uri uri) {
        if (uri != null) {
            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Log.d("CameraActivity", "tempUri" + uri.getPath());
            Intent intent = new Intent();
            intent.putExtra("data", uri);
            setResult(10, intent);
            finish();
            Log.d("PhotoPicker", "Selected URI: " + uri);
            return;
        }
        Log.d("PhotoPicker", "No media selected");
    }

    public void openStorage() {
        this.pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());
    }

    private void startCamera() {
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(this);
        processCameraProvider.addListener(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$$ExternalSyntheticLambda1
            @Override 
            public final void run() {
                CameraActivity.this.m901x3a02f032(processCameraProvider);
            }
        }, ContextCompat.getMainExecutor(this));
    }

    
    /* renamed from: lambda$startCamera$4$com-exorium-plant_recognition-activities-CameraActivity  reason: not valid java name */
    public  void m901x3a02f032(ListenableFuture listenableFuture) {
        ProcessCameraProvider processCameraProvider;
        try {
            processCameraProvider = (ProcessCameraProvider) listenableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("CameraActivity", "Use case binding failed", e);
            processCameraProvider = null;
        }
        Preview build = new Preview.Builder().build();
        this.imageCapture = new ImageCapture.Builder().build();
        CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        build.setSurfaceProvider(this.previewView.getSurfaceProvider());
        if (processCameraProvider != null) {
            try {
                processCameraProvider.unbindAll();
                processCameraProvider.bindToLifecycle(this, cameraSelector, build, this.imageCapture);
            } catch (Exception e2) {
                Log.e("CameraActivity", "Use case binding failed", e2);
            }
        }
    }

    private void takePhoto() {
        ImageCapture imageCapture = this.imageCapture;
        if (imageCapture == null) {
            return;
        }
        File file = new File(this.outputDirectory, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(Long.valueOf(System.currentTimeMillis())) + ".jpg");
        imageCapture.takePicture(new ImageCapture.OutputFileOptions.Builder(file).build(), ContextCompat.getMainExecutor(this), new AnonymousClass2(file));
    }

    
    /* renamed from: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$2  reason: invalid class name */

    public class AnonymousClass2 implements ImageCapture.OnImageSavedCallback {
        final  File val$photoFile;

        AnonymousClass2(final File val$photoFile) {
            this.val$photoFile = val$photoFile;
        }

        @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
        public void onError(ImageCaptureException exc) {
            Toast.makeText(CameraActivity.this.getApplicationContext(), "Photo capture failed: " + exc.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("CameraActivity", "Photo capture failed: " + exc.getMessage(), exc);
        }

        @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
        public void onImageSaved(ImageCapture.OutputFileResults output) {
            final Uri fromFile = Uri.fromFile(this.val$photoFile);
            String str = "Photo capture succeeded: " + fromFile;
            final Dialog dialog = new Dialog(CameraActivity.this);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.dialog_image_preview);
            Window window = dialog.getWindow();
            if (window == null) {
                return;
            }
            window.setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(0));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = Gravity.CENTER;
            window.setAttributes(attributes);
            dialog.setCancelable(true);
            ((Button) dialog.findViewById(R.id.btn_select)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$2$$ExternalSyntheticLambda0
                @Override 
                public final void onClick(View view) {
                    AnonymousClass2.this.m902x5560e5a(dialog, fromFile, view);
                }
            });
            ((Button) dialog.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.activities.CameraActivity$2$$ExternalSyntheticLambda1
                @Override 
                public final void onClick(View view) {
                    dialog.dismiss();
                }
            });
            ImageView imageView = (ImageView) dialog.findViewById(R.id.image_preview);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageURI(fromFile);
            dialog.show();
            Log.d("CameraActivity", str);
        }

        
        /* renamed from: lambda$onImageSaved$0$com-exorium-plant_recognition-activities-CameraActivity$2  reason: not valid java name */
        public  void m902x5560e5a(Dialog dialog, Uri uri, View view) {
            dialog.dismiss();
            Intent intent = new Intent();
            intent.putExtra("data", uri);
            CameraActivity.this.setResult(10, intent);
            CameraActivity.this.finish();
        }
    }

    private File getOutputDirectory() {
        File file;
        File[] externalMediaDirs = getApplicationContext().getExternalMediaDirs();
        String string = getApplicationContext().getResources().getString(R.string.app_name);
        if (externalMediaDirs.length > 0) {
            file = new File(externalMediaDirs[0], string);
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            file = null;
        }
        return (file == null || !file.exists()) ? getApplicationContext().getFilesDir() : file;
    }

    
    @Override 
    public void onDestroy() {
        super.onDestroy();
        ExecutorService executorService = this.cameraExecutor;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override 
    public void onStart() {
        super.onStart();

    }

    @Override 
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override 
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                startCamera();
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 200 || requestCode == 300) {
            if (grantResults.length > 0 && grantResults[0] == 0) {
                openStorage();
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1 && data != null) {
            Uri data2 = data.getData();
            getContentResolver().takePersistableUriPermission(data2, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Log.d("CameraActivity", "tempUri" + data2.getPath());
            Intent intent = new Intent();
            intent.putExtra("data", data2);
            setResult(10, intent);
            finish();
        }
    }
}
