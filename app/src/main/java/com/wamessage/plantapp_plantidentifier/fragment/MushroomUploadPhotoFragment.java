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
import com.wamessage.plantapp_plantidentifier.activities.MushroomRecognitionActivity;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.models.Mushroom;
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


public class MushroomUploadPhotoFragment extends Fragment {
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;
    private static final float PROBABILITY_MEAN = 0.0f;
    private static final float PROBABILITY_STD = 255.0f;
    private static final String TAG = "MushroomUploadPhotoFragment";
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
    Mushroom mushroomBest;
    MushroomRecognitionActivity mushroomRecognitionActivity;
    MyModal myModal;
    private TensorBuffer outputProbabilityBuffer;
    private TensorProcessor probabilityProcessor;
    protected Interpreter tflite;
    private final int REQUEST_CODE_PICK_IMAGE = 10;
    public List<Mushroom> mushrooms = new ArrayList();

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mushroom_upload_photo, container, false);
        this.mView = inflate;
        this.btn_selectSource = (Button) inflate.findViewById(R.id.btn_selectSource);
        MushroomRecognitionActivity mushroomRecognitionActivity = (MushroomRecognitionActivity) getActivity();
        this.mushroomRecognitionActivity = mushroomRecognitionActivity;
        if (this.cropImage == null) {
            this.cropImage = mushroomRecognitionActivity.registerForActivityResult(new CropImageContract(), new ActivityResultCallback() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MushroomUploadPhotoFragment$$ExternalSyntheticLambda0
                @Override // androidx.activity.result.ActivityResultCallback
                public final void onActivityResult(Object obj) {
                    MushroomUploadPhotoFragment.this.m1007x936f555f((CropImageView.CropResult) obj);
                }
            });
        }
        this.myModal = new MyModal(this.mushroomRecognitionActivity);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MushroomUploadPhotoFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                MushroomUploadPhotoFragment.this.m1008x2fdd51be(view);
            }
        });
        toolbar.setTitle(R.string.mushroom_recognition);
        openCamera();
        this.btn_selectSource.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MushroomUploadPhotoFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                MushroomUploadPhotoFragment.this.m1009xcc4b4e1d(view);
            }
        });
        return this.mView;
    }

    
    /* renamed from: lambda$onCreateView$0$com-exorium-plant_recognition-fragment-MushroomUploadPhotoFragment  reason: not valid java name */
    public  void m1007x936f555f(CropImageView.CropResult cropResult) {
        if (cropResult.isSuccessful()) {
            try {
                this.imageuri = cropResult.getUriContent();
                this.imagebitmap = MediaStore.Images.Media.getBitmap(this.mushroomRecognitionActivity.getContentResolver(), this.imageuri);
                doRecognize();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Crop error: " + e.getMessage());
                Toast.makeText(this.mushroomRecognitionActivity, "Crop error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                openCamera();
                return;
            }
        }
        Exception error = cropResult.getError();
        Log.e(TAG, "Crop error: " + error.getMessage());
        Toast.makeText(this.mushroomRecognitionActivity, "Crop error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        openCamera();
    }

    
    /* renamed from: lambda$onCreateView$1$com-exorium-plant_recognition-fragment-MushroomUploadPhotoFragment  reason: not valid java name */
    public  void m1008x2fdd51be(View view) {
        getActivity().finish();
    }

    
    /* renamed from: lambda$onCreateView$2$com-exorium-plant_recognition-fragment-MushroomUploadPhotoFragment  reason: not valid java name */
    public  void m1009xcc4b4e1d(View view) {
        openCamera();
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadTFLiteModel();
    }

    public void openCamera() {
        startActivityForResult(new Intent(this.mushroomRecognitionActivity, CameraActivity.class), 10);
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
    public void openPlantIdentifierFragment(List<Mushroom> mushrooms) {
        MushroomIdentifierFragment mushroomIdentifierFragment = new MushroomIdentifierFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mushroomBest", this.mushroomBest);
        bundle.putSerializable("mushrooms", (Serializable) mushrooms);
        bundle.putByteArray("img", this.imageByte);
        mushroomIdentifierFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.addToBackStack(MushroomIdentifierFragment.TAG);
        beginTransaction.replace(R.id.content_frame, mushroomIdentifierFragment);
        beginTransaction.commit();
    }

    private void loadTFLiteModel() {
        try {
            this.tflite = new Interpreter(loadmodelfile(this.mushroomRecognitionActivity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadmodelfile(Activity activity) throws IOException {
        AssetFileDescriptor openFd = activity.getAssets().openFd("mushroom_pro_model.tflite");
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
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.wamessage.plantapp_plantidentifier.fragment.MushroomUploadPhotoFragment.1
            @Override 
            public void run() {
                try {
                    MushroomUploadPhotoFragment mushroomUploadPhotoFragment = MushroomUploadPhotoFragment.this;
                    mushroomUploadPhotoFragment.labels = FileUtil.loadLabels(mushroomUploadPhotoFragment.mushroomRecognitionActivity, "mushroom_pro_label.txt");
                    MushroomUploadPhotoFragment.this.mushrooms.clear();
                    int i = 0;
                    for (Map.Entry entry : new TensorLabel(MushroomUploadPhotoFragment.this.labels, MushroomUploadPhotoFragment.this.probabilityProcessor.process(MushroomUploadPhotoFragment.this.outputProbabilityBuffer)).getMapWithFloatValue().entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList())) {
                        MushroomUploadPhotoFragment.this.mushrooms.add(new Mushroom((String) entry.getKey(), Double.valueOf(Double.parseDouble(((Float) entry.getValue()).toString()) * 100.0d)));
                        i++;
                        if (i >= 10) {
                            break;
                        }
                    }
                    MushroomUploadPhotoFragment mushroomUploadPhotoFragment2 = MushroomUploadPhotoFragment.this;
                    mushroomUploadPhotoFragment2.imagebitmap = mushroomUploadPhotoFragment2.getResizedBitmap(mushroomUploadPhotoFragment2.imagebitmap, 200);
                    MushroomUploadPhotoFragment mushroomUploadPhotoFragment3 = MushroomUploadPhotoFragment.this;
                    mushroomUploadPhotoFragment3.imageByte = mushroomUploadPhotoFragment3.Bitmap_To_Byte(mushroomUploadPhotoFragment3.imagebitmap);
                    MushroomUploadPhotoFragment.this.mushroomBest = new Mushroom(MushroomUploadPhotoFragment.this.imageByte, MushroomUploadPhotoFragment.this.mushrooms.get(0).getName(), MushroomUploadPhotoFragment.this.mushrooms.get(0).getScore());
                    if (AppDatabase.getInstance(MushroomUploadPhotoFragment.this.mushroomRecognitionActivity).mushroomDao().getMushroomCount() > 10) {
                        AppDatabase.getInstance(MushroomUploadPhotoFragment.this.mushroomRecognitionActivity).mushroomDao().deleteMushroom(AppDatabase.getInstance(MushroomUploadPhotoFragment.this.mushroomRecognitionActivity).mushroomDao().getFirstMushroom());
                    }
                    MushroomUploadPhotoFragment.this.mushroomBest.setId(AppDatabase.getInstance(MushroomUploadPhotoFragment.this.mushroomRecognitionActivity).mushroomDao().insertMushroom(MushroomUploadPhotoFragment.this.mushroomBest));
                    Log.e("Mushroom_Recognition_Unknown", "Result: " + MushroomUploadPhotoFragment.this.mushrooms);
                    if (MushroomUploadPhotoFragment.this.mushrooms.size() > 0) {
                        for (Mushroom mushroom : MushroomUploadPhotoFragment.this.mushrooms) {
                            if (Objects.equals(mushroom.getName(), "unknow") || Objects.equals(mushroom.getName(), "unknown")) {
                                double convertFloatToPercent = ConvertUtils.convertFloatToPercent(mushroom.getScore().doubleValue());

                                Log.e("Mushroom_Recognition_Unknown", "NameType: " + mushroom.getName() + "/ PercentType: " + convertFloatToPercent);
                            }
                        }
                    }
                    MushroomUploadPhotoFragment mushroomUploadPhotoFragment4 = MushroomUploadPhotoFragment.this;
                    mushroomUploadPhotoFragment4.openPlantIdentifierFragment(mushroomUploadPhotoFragment4.mushrooms);
                    MushroomUploadPhotoFragment.this.myModal.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        newSingleThreadExecutor.shutdown();
    }
}
