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
import com.wamessage.plantapp_plantidentifier.activities.InsectRecognitionActivity;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.models.Insect;
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


public class InsectUploadPhotoFragment extends Fragment {
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;
    private static final float PROBABILITY_MEAN = 0.0f;
    private static final float PROBABILITY_STD = 255.0f;
    private static final String TAG = "InsectUploadPhotoFragment";
    private Button btn_selectSource;
    private ActivityResultLauncher<CropImageContractOptions> cropImage;
    byte[] imageByte;
    private int imageSizeX;
    private int imageSizeY;
    Bitmap imagebitmap;
    Uri imageuri;
    private TensorImage inputImageBuffer;
    private Insect insectBest;
    InsectRecognitionActivity insectRecognitionActivity;
    private List<String> labels;
    View mView;
    MyModal myModal;
    private TensorBuffer outputProbabilityBuffer;
    private TensorProcessor probabilityProcessor;
    protected Interpreter tfLite;
    private final int REQUEST_CODE_PICK_IMAGE = 10;
    public List<Insect> insects = new ArrayList();

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_insect_upload_photo, container, false);
        this.mView = inflate;
        this.btn_selectSource = (Button) inflate.findViewById(R.id.btn_selectSource);
        InsectRecognitionActivity insectRecognitionActivity = (InsectRecognitionActivity) getActivity();
        this.insectRecognitionActivity = insectRecognitionActivity;
        if (this.cropImage == null) {
            this.cropImage = insectRecognitionActivity.registerForActivityResult(new CropImageContract(), new ActivityResultCallback() { // from class: com.wamessage.plantapp_plantidentifier.fragment.InsectUploadPhotoFragment$$ExternalSyntheticLambda0
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    InsectUploadPhotoFragment.this.m995xde876c4f((CropImageView.CropResult) obj);
                }
            });
        }
        this.myModal = new MyModal(this.insectRecognitionActivity);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.InsectUploadPhotoFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                InsectUploadPhotoFragment.this.m996x48b6f46e(view);
            }
        });
        toolbar.setTitle(R.string.insect_recognition);
        openCamera();
        this.btn_selectSource.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.InsectUploadPhotoFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                InsectUploadPhotoFragment.this.m997xb2e67c8d(view);
            }
        });
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-InsectUploadPhotoFragment  reason: not valid java name */
    public  void m995xde876c4f(CropImageView.CropResult cropResult) {
        if (cropResult.isSuccessful()) {
            try {
                this.imageuri = cropResult.getUriContent();
                this.imagebitmap = MediaStore.Images.Media.getBitmap(this.insectRecognitionActivity.getContentResolver(), this.imageuri);
                doRecognize();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Crop error: " + e.getMessage());
                Toast.makeText(this.insectRecognitionActivity, "Crop error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                openCamera();
                return;
            }
        }
        Exception error = cropResult.getError();
        Log.e(TAG, "Crop error: " + error.getMessage());
        Toast.makeText(this.insectRecognitionActivity, "Crop error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        openCamera();
    }

    
    /* renamed from: lambda$onCreateView$1$com-exorium-plant_recognition-fragment-InsectUploadPhotoFragment  reason: not valid java name */
    public  void m996x48b6f46e(View view) {
        getActivity().finish();
    }

    
    /* renamed from: lambda$onCreateView$2$com-exorium-plant_recognition-fragment-InsectUploadPhotoFragment  reason: not valid java name */
    public  void m997xb2e67c8d(View view) {
        openCamera();
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadTFLiteModel();
    }

    public void openCamera() {
        startActivityForResult(new Intent(this.insectRecognitionActivity, CameraActivity.class), 10);
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
    @Override 
    public void onResume() {
        super.onResume();
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

    private void openPlantIdentifierFragment(List<Insect> insects) {
        InsectIdentifierFragment insectIdentifierFragment = new InsectIdentifierFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("insectBest", this.insectBest);
        bundle.putSerializable("insects", (Serializable) insects);
        bundle.putByteArray("img", this.imageByte);
        insectIdentifierFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.addToBackStack(InsectIdentifierFragment.TAG);
        beginTransaction.replace(R.id.content_frame, insectIdentifierFragment);
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
        AssetFileDescriptor openFd = activity.getAssets().openFd("insect_pro_model.tflite");
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
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.fragment.InsectUploadPhotoFragment$$ExternalSyntheticLambda3
            @Override 
            public final void run() {
                InsectUploadPhotoFragment.this.m994xd785ae53();
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    
    /* renamed from: lambda$getResult$3$com-exorium-plant_recognition-fragment-InsectUploadPhotoFragment  reason: not valid java name */
    public  void m994xd785ae53() {
        try {
            this.labels = FileUtil.loadLabels(requireActivity(), "insect_pro_label.txt");
            this.insects.clear();
            int i = 0;
            for (Map.Entry entry : new TensorLabel(this.labels, this.probabilityProcessor.process(this.outputProbabilityBuffer)).getMapWithFloatValue().entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList())) {
                this.insects.add(new Insect((String) entry.getKey(), Double.valueOf(Double.parseDouble(((Float) entry.getValue()).toString()))));
                i++;
                if (i >= 10) {
                    break;
                }
            }
            Bitmap resizedBitmap = getResizedBitmap(this.imagebitmap, 200);
            this.imagebitmap = resizedBitmap;
            this.imageByte = bitmapToByte(resizedBitmap);
            if (Objects.equals(this.insects.get(0).getName(), "unknown")) {
                this.insectBest = new Insect(this.imageByte, this.insects.get(1).getName(), Double.valueOf(this.insects.get(1).getScore().doubleValue() == 0.0d ? generateRandomDoubleWithStep() : this.insects.get(1).getScore().doubleValue()));
            } else {
                this.insectBest = this.insects.get(0);
            }
            if (AppDatabase.getInstance(requireActivity()).insectDao().getInsectCount() > 10) {
                AppDatabase.getInstance(requireActivity()).insectDao().deleteInsect(AppDatabase.getInstance(requireActivity()).insectDao().getFirstInsect());
            }
            this.insectBest.setId(AppDatabase.getInstance(requireActivity()).insectDao().insertInsect(this.insectBest));
            Log.e("Insect_Recognition_Unknown", "Result: " + this.insects);
            List<Insect> list = this.insects;
            if (list != null && list.size() > 0) {
                for (Insect insect : this.insects) {
                    if (Objects.equals(insect.getName(), "unknow") || Objects.equals(insect.getName(), "unknown")) {
                        double convertFloatToPercent = ConvertUtils.convertFloatToPercent(insect.getScore().doubleValue());

                        Log.e("Insect_Recognition_Unknown", "NameType: " + insect.getName() + "/ PercentType: " + convertFloatToPercent);
                    }
                }
            }
            openPlantIdentifierFragment(this.insects);
            this.myModal.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double generateRandomDoubleWithStep() {
        return (new Random().nextInt(46) * 0.1d) + 0.5d;
    }
}
