package com.wamessage.plantapp_plantidentifier.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.CropImageContractOptions;
import com.canhub.cropper.CropImageOptions;
import com.canhub.cropper.CropImageView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.activities.CameraActivity;
import com.wamessage.plantapp_plantidentifier.activities.PlantHealthActivity;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.models.PlantHealth;
import com.wamessage.plantapp_plantidentifier.utils.ConvertUtils;
import com.wamessage.plantapp_plantidentifier.utils.MyModal;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.common.TensorOperator;
import org.tensorflow.lite.support.common.TensorProcessor;
import org.tensorflow.lite.support.common.ops.NormalizeOp;
import org.tensorflow.lite.support.image.ImageOperator;
import org.tensorflow.lite.support.image.ImageProcessor;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.image.ops.ResizeOp;
import org.tensorflow.lite.support.image.ops.ResizeWithCropOrPadOp;
import org.tensorflow.lite.support.label.TensorLabel;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;


public class PlantHealthUploadPhotoFragment extends Fragment {
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;
    private static final float PROBABILITY_MEAN = 0.0f;
    private static final float PROBABILITY_STD = 255.0f;
    private static final String TAG = "PlantHealthUploadPhotoFragment";
    private Button btn_selectSource;
    private ActivityResultLauncher<CropImageContractOptions> cropImage;
    byte[] imageByte;
    private int imageSizeX;
    private int imageSizeY;
    Bitmap imagebitmap;
    Uri imageuri;
    private TensorImage inputImageBuffer;
    private List<String> labels;
    View mView;
    MyModal myModal;
    private TensorBuffer outputProbabilityBuffer;
    PlantHealthActivity plantHealthActivity;
    PlantHealth plantHealthBest;
    private TensorProcessor probabilityProcessor;
    protected Interpreter tflite;
    private final int REQUEST_CODE_PICK_IMAGE = 10;
    public List<PlantHealth> plantHealths = new ArrayList();

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_plant_health_upload_photo, container, false);
        this.mView = inflate;
        this.btn_selectSource = (Button) inflate.findViewById(R.id.btn_selectSource);
        PlantHealthActivity plantHealthActivity = (PlantHealthActivity) getActivity();
        this.plantHealthActivity = plantHealthActivity;
        if (this.cropImage == null) {
            this.cropImage = plantHealthActivity.registerForActivityResult(new CropImageContract(), new ActivityResultCallback() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthUploadPhotoFragment$$ExternalSyntheticLambda0
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    PlantHealthUploadPhotoFragment.this.m1014x5fd86384((CropImageView.CropResult) obj);
                }
            });
        }
        this.myModal = new MyModal(this.plantHealthActivity);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthUploadPhotoFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                PlantHealthUploadPhotoFragment.this.m1015x430416c5(view);
            }
        });
        toolbar.setTitle(R.string.plant_health);
        openCamera();
        this.btn_selectSource.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthUploadPhotoFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                PlantHealthUploadPhotoFragment.this.m1016x262fca06(view);
            }
        });
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-PlantHealthUploadPhotoFragment  reason: not valid java name */
    public  void m1014x5fd86384(CropImageView.CropResult cropResult) {
        if (cropResult.isSuccessful()) {
            try {
                this.imageuri = cropResult.getUriContent();
                this.imagebitmap = MediaStore.Images.Media.getBitmap(this.plantHealthActivity.getContentResolver(), this.imageuri);
                doRecognize();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Crop error: " + e.getMessage());
                Toast.makeText(this.plantHealthActivity, "Crop error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                openCamera();
                return;
            }
        }
        Exception error = cropResult.getError();
        Log.e(TAG, "Crop error: " + error.getMessage());
        Toast.makeText(this.plantHealthActivity, "Crop error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        openCamera();
    }

    
    /* renamed from: lambda$onCreateView$1$com-exorium-plant_recognition-fragment-PlantHealthUploadPhotoFragment  reason: not valid java name */
    public  void m1015x430416c5(View view) {
        getActivity().finish();
    }

    
    /* renamed from: lambda$onCreateView$2$com-exorium-plant_recognition-fragment-PlantHealthUploadPhotoFragment  reason: not valid java name */
    public  void m1016x262fca06(View view) {
        openCamera();
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadTFLiteModel();
    }

    public void openCamera() {
        startActivityForResult(new Intent(this.plantHealthActivity, CameraActivity.class), 10);
    }

    @Override 
    public void onResume() {
        super.onResume();
    }

    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (data != null) {
                try {
                    launchImageCrop((Uri) data.getExtras().get("data"));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            requireActivity().finish();
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

    private void launchImageCrop(Uri uri) {
        this.cropImage.launch(new CropImageContractOptions(uri, new CropImageOptions()));
    }

    private Uri getImageUri(Context context, Bitmap bitmap) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        return Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "IMG_" + Calendar.getInstance().getTime(), (String) null));
    }

    public byte[] Bitmap_To_Byte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openPlantIdentifierFragment(List<PlantHealth> plantHealths) {
        PlantHealthFragment plantHealthFragment = new PlantHealthFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("plantHealthBest", this.plantHealthBest);
        bundle.putSerializable("plantHealths", (Serializable) plantHealths);
        bundle.putByteArray("img", this.imageByte);
        plantHealthFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.addToBackStack(PlantHealthFragment.TAG);
        beginTransaction.replace(R.id.content_frame, plantHealthFragment);
        beginTransaction.commit();
    }

    private void loadTFLiteModel() {
        try {
            this.tflite = new Interpreter(loadmodelfile(this.plantHealthActivity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadmodelfile(Activity activity) throws IOException {
        AssetFileDescriptor openFd = activity.getAssets().openFd("health_pro_model.tflite");
        return new FileInputStream(openFd.getFileDescriptor()).getChannel().map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [org.tensorflow.lite.support.common.TensorProcessor] */
    private void doRecognize() {
        int[] shape = this.tflite.getInputTensor(0).shape();
        this.imageSizeY = shape[1];
        this.imageSizeX = shape[2];
        DataType dataType = this.tflite.getInputTensor(0).dataType();
        int[] shape2 = this.tflite.getOutputTensor(0).shape();
        DataType dataType2 = this.tflite.getOutputTensor(0).dataType();
        this.inputImageBuffer = new TensorImage(dataType);
        this.outputProbabilityBuffer = TensorBuffer.createFixedSize(shape2, dataType2);
        this.probabilityProcessor = new TensorProcessor.Builder().add(getPostprocessNormalizeOp()).build();
        TensorImage loadImage = loadImage(this.imagebitmap);
        this.inputImageBuffer = loadImage;
        this.tflite.run(loadImage.getBuffer(), this.outputProbabilityBuffer.getBuffer().rewind());
        getResult();
    }

    private TensorOperator getPostprocessNormalizeOp() {
        return new NormalizeOp(0.0f, (float) PROBABILITY_STD);
    }

    private TensorImage loadImage(final Bitmap bitmap) {
        this.inputImageBuffer.load(bitmap);
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        return new ImageProcessor.Builder().add((ImageOperator) new ResizeWithCropOrPadOp(min, min)).add((ImageOperator) new ResizeOp(this.imageSizeX, this.imageSizeY, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR)).add(getPreprocessNormalizeOp()).build().process(this.inputImageBuffer);
    }

    private TensorOperator getPreprocessNormalizeOp() {
        return new NormalizeOp(0.0f, 1.0f);
    }

    private void getResult() {
        this.myModal.show();
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantHealthUploadPhotoFragment.1
            @Override 
            public void run() {
                try {
                    PlantHealthUploadPhotoFragment plantHealthUploadPhotoFragment = PlantHealthUploadPhotoFragment.this;
                    plantHealthUploadPhotoFragment.labels = FileUtil.loadLabels(plantHealthUploadPhotoFragment.plantHealthActivity, "health_pro_label.txt");
                    PlantHealthUploadPhotoFragment.this.plantHealths.clear();
                    int i = 0;
                    for (Map.Entry entry :  new TensorLabel(PlantHealthUploadPhotoFragment.this.labels, PlantHealthUploadPhotoFragment.this.probabilityProcessor.process(PlantHealthUploadPhotoFragment.this.outputProbabilityBuffer)).getMapWithFloatValue().entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList())) {
                        PlantHealthUploadPhotoFragment.this.plantHealths.add(new PlantHealth((String) entry.getKey(), Double.parseDouble(((Float) entry.getValue()).toString()) * 100.0d));
                        i++;
                        if (i >= 10) {
                            break;
                        }
                    }
                    PlantHealthUploadPhotoFragment plantHealthUploadPhotoFragment2 = PlantHealthUploadPhotoFragment.this;
                    plantHealthUploadPhotoFragment2.imagebitmap = plantHealthUploadPhotoFragment2.getResizedBitmap(plantHealthUploadPhotoFragment2.imagebitmap, 200);
                    PlantHealthUploadPhotoFragment plantHealthUploadPhotoFragment3 = PlantHealthUploadPhotoFragment.this;
                    plantHealthUploadPhotoFragment3.imageByte = plantHealthUploadPhotoFragment3.Bitmap_To_Byte(plantHealthUploadPhotoFragment3.imagebitmap);
                    PlantHealthUploadPhotoFragment.this.plantHealthBest = new PlantHealth(PlantHealthUploadPhotoFragment.this.imageByte, PlantHealthUploadPhotoFragment.this.plantHealths.get(0).getName(), PlantHealthUploadPhotoFragment.this.plantHealths.get(0).getScore());
                    if (AppDatabase.getInstance(PlantHealthUploadPhotoFragment.this.plantHealthActivity).plantHealthDao().getPlantHealthCount() > 10) {
                        AppDatabase.getInstance(PlantHealthUploadPhotoFragment.this.plantHealthActivity).plantHealthDao().deletePlantHealth(AppDatabase.getInstance(PlantHealthUploadPhotoFragment.this.plantHealthActivity).plantHealthDao().getFirstPlantHealth());
                    }
                    PlantHealthUploadPhotoFragment.this.plantHealthBest.setId(AppDatabase.getInstance(PlantHealthUploadPhotoFragment.this.plantHealthActivity).plantHealthDao().insertPlantHealth(PlantHealthUploadPhotoFragment.this.plantHealthBest));
                    Log.e("PlantHeath_Recognition_Unknown", "Result: " + PlantHealthUploadPhotoFragment.this.plantHealths);
                    if (PlantHealthUploadPhotoFragment.this.plantHealths != null && PlantHealthUploadPhotoFragment.this.plantHealths.size() > 0) {
                        for (PlantHealth plantHealth : PlantHealthUploadPhotoFragment.this.plantHealths) {
                            if (Objects.equals(plantHealth.getName(), "unknow") || Objects.equals(plantHealth.getName(), "unknown")) {
                                double convertFloatToPercent = ConvertUtils.convertFloatToPercent(plantHealth.getScore().doubleValue());

                                Log.e("PlantHeath_Recognition_Unknown", "NameType: " + plantHealth.getScore() + "/ PercentType: " + convertFloatToPercent);
                            }
                        }
                    }
                    PlantHealthUploadPhotoFragment plantHealthUploadPhotoFragment4 = PlantHealthUploadPhotoFragment.this;
                    plantHealthUploadPhotoFragment4.openPlantIdentifierFragment(plantHealthUploadPhotoFragment4.plantHealths);
                    PlantHealthUploadPhotoFragment.this.myModal.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        newSingleThreadExecutor.shutdown();
    }
}
