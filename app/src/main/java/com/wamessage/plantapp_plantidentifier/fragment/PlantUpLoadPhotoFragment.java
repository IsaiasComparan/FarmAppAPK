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
import com.wamessage.plantapp_plantidentifier.activities.PlantRecognitionActivity;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.models.Plant;
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
import java.util.Random;
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


public class PlantUpLoadPhotoFragment extends Fragment {
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;
    private static final float PROBABILITY_MEAN = 0.0f;
    private static final float PROBABILITY_STD = 255.0f;
    private static final String TAG = "PlantUpLoadPhotoFragment";
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
    Plant plantBest;
    PlantRecognitionActivity plantRecognitionActivity;
    private TensorProcessor probabilityProcessor;
    protected Interpreter tfLite;
    private final int REQUEST_CODE_PICK_IMAGE = 10;
    public List<Plant> plants = new ArrayList();

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_up_load_photo, container, false);
        this.mView = inflate;
        this.btn_selectSource = (Button) inflate.findViewById(R.id.btn_selectSource);
        PlantRecognitionActivity plantRecognitionActivity = (PlantRecognitionActivity) getActivity();
        this.plantRecognitionActivity = plantRecognitionActivity;
        if (this.cropImage == null) {
            this.cropImage = plantRecognitionActivity.registerForActivityResult(new CropImageContract(), new ActivityResultCallback() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantUpLoadPhotoFragment$$ExternalSyntheticLambda0
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    PlantUpLoadPhotoFragment.this.m1022x336746a0((CropImageView.CropResult) obj);
                }
            });
        }
        this.myModal = new MyModal(this.plantRecognitionActivity);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantUpLoadPhotoFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                PlantUpLoadPhotoFragment.this.m1023x8126bea1(view);
            }
        });
        toolbar.setTitle(R.string.plant_recognition);
        openCamera();
        this.btn_selectSource.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantUpLoadPhotoFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                PlantUpLoadPhotoFragment.this.m1024xcee636a2(view);
            }
        });
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-PlantUpLoadPhotoFragment  reason: not valid java name */
    public  void m1022x336746a0(CropImageView.CropResult cropResult) {
        if (cropResult.isSuccessful()) {
            try {
                this.imageuri = cropResult.getUriContent();
                this.imagebitmap = MediaStore.Images.Media.getBitmap(this.plantRecognitionActivity.getContentResolver(), this.imageuri);
                doRecognize();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Crop error: " + e.getMessage());
                Toast.makeText(this.plantRecognitionActivity, "Crop error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                openCamera();
                return;
            }
        }
        Exception error = cropResult.getError();
        Log.e(TAG, "Crop error: " + error.getMessage());
        Toast.makeText(this.plantRecognitionActivity, "Crop error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        openCamera();
    }

    
    /* renamed from: lambda$onCreateView$1$com-exorium-plant_recognition-fragment-PlantUpLoadPhotoFragment  reason: not valid java name */
    public  void m1023x8126bea1(View view) {
        getActivity().finish();
    }

    
    /* renamed from: lambda$onCreateView$2$com-exorium-plant_recognition-fragment-PlantUpLoadPhotoFragment  reason: not valid java name */
    public  void m1024xcee636a2(View view) {
        openCamera();
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadTFLiteModel();
    }

    public void openCamera() {
        startActivityForResult(new Intent(requireContext(), CameraActivity.class), 10);
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

    public byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void openPlantIdentifierFragment(List<Plant> plants) {
        PlantIdentifierFragment plantIdentifierFragment = new PlantIdentifierFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("plantBest", this.plantBest);
        bundle.putSerializable("plants", (Serializable) plants);
        Log.e("TAG", "openPlantIdentifierFragment");
        bundle.putByteArray("img", this.imageByte);
        Log.e("TAG", "finish openPlantIdentifierFragment");
        plantIdentifierFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.addToBackStack(PlantIdentifierFragment.TAG);
        beginTransaction.replace(R.id.content_frame, plantIdentifierFragment);
        beginTransaction.commit();
    }

    private void openNotifyInternetFragment(Bitmap bitmap) {
        NotiInternetFragment notiInternetFragment = new NotiInternetFragment();
        Bundle bundle = new Bundle();
        bundle.putByteArray("img", bitmapToByte(bitmap));
        notiInternetFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.addToBackStack(NotiInternetFragment.TAG);
        beginTransaction.replace(R.id.content_frame, notiInternetFragment);
        beginTransaction.commit();
    }

    private void loadTFLiteModel() {
        try {
            this.tfLite = new Interpreter(loadModelFile(requireActivity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadModelFile(Activity activity) throws IOException {
        AssetFileDescriptor openFd = activity.getAssets().openFd("plant_pro_model.tflite");
        return new FileInputStream(openFd.getFileDescriptor()).getChannel().map(FileChannel.MapMode.READ_ONLY, openFd.getStartOffset(), openFd.getDeclaredLength());
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [org.tensorflow.lite.support.common.TensorProcessor] */
    private void doRecognize() {
        int[] shape = this.tfLite.getInputTensor(0).shape();
        this.imageSizeY = shape[1];
        this.imageSizeX = shape[2];
        DataType dataType = this.tfLite.getInputTensor(0).dataType();
        int[] shape2 = this.tfLite.getOutputTensor(0).shape();
        DataType dataType2 = this.tfLite.getOutputTensor(0).dataType();
        this.inputImageBuffer = new TensorImage(dataType);
        this.outputProbabilityBuffer = TensorBuffer.createFixedSize(shape2, dataType2);
        this.probabilityProcessor = new TensorProcessor.Builder().add(getPostprocessNormalizeOp()).build();
        TensorImage loadImage = loadImage(this.imagebitmap);
        this.inputImageBuffer = loadImage;
        this.tfLite.run(loadImage.getBuffer(), this.outputProbabilityBuffer.getBuffer().rewind());
        Log.e("TAG", "getResult");
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

    void getResult() {
        this.myModal.show();
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.fragment.PlantUpLoadPhotoFragment$$ExternalSyntheticLambda3
            @Override 
            public final void run() {
                PlantUpLoadPhotoFragment.this.m1021xd856b41c();
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    
    /* renamed from: lambda$getResult$3$com-exorium-plant_recognition-fragment-PlantUpLoadPhotoFragment  reason: not valid java name */
    public  void m1021xd856b41c() {
        try {
            Log.e("TAG", "start loadLabels");
            this.labels = FileUtil.loadLabels(requireActivity(), "plant_pro_label.txt");
            Log.e("TAG", "finish loadLabels");
            this.plants.clear();
            int i = 0;
            for (Map.Entry entry :  new TensorLabel(this.labels, this.probabilityProcessor.process(this.outputProbabilityBuffer)).getMapWithFloatValue().entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList())) {
                this.plants.add(new Plant((String) entry.getKey(), Double.valueOf(Double.parseDouble(((Float) entry.getValue()).toString()))));
                i++;
                if (i >= 10) {
                    break;
                }
            }
            Bitmap resizedBitmap = getResizedBitmap(this.imagebitmap, 200);
            this.imagebitmap = resizedBitmap;
            this.imageByte = bitmapToByte(resizedBitmap);
            if (this.plants.get(0).getName().equals("unknow")) {
                this.plantBest = new Plant(this.imageByte, this.plants.get(1).getName(), Double.valueOf(this.plants.get(1).getScore().doubleValue() == 0.0d ? generateRandomDoubleWithStep() : this.plants.get(1).getScore().doubleValue()));
            } else {
                this.plantBest = this.plants.get(0);
            }
            if (AppDatabase.getInstance(requireActivity()).plantDao().getPlantCount() > 10) {
                AppDatabase.getInstance(requireActivity()).plantDao().deletePlant(AppDatabase.getInstance(requireActivity()).plantDao().getFirstPlant());
            }
            this.plantBest.setId(AppDatabase.getInstance(requireActivity()).plantDao().insertPlant(this.plantBest));
            Log.e("Plant_Recognition_Unknown", "Result: " + this.plants);
            if (this.plants.size() > 0) {
                for (Plant plant : this.plants) {
                    if (Objects.equals(plant.getName(), "unknow") || Objects.equals(plant.getName(), "unknown")) {
                        double convertFloatToPercent = ConvertUtils.convertFloatToPercent(plant.getScore().doubleValue());

                        Log.e("Plant_Recognition_Unknown", "NameType: " + plant.getName() + "/ PercentType: " + convertFloatToPercent);
                    }
                }
            }
            openPlantIdentifierFragment(this.plants);
            this.myModal.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double generateRandomDoubleWithStep() {
        return (new Random().nextInt(46) * 0.1d) + 0.5d;
    }
}
