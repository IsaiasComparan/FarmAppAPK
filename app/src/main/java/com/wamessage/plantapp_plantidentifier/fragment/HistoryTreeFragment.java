package com.wamessage.plantapp_plantidentifier.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.adapter.HistoryTreeAdapter;
import com.wamessage.plantapp_plantidentifier.datatbase.AppDatabase;
import com.wamessage.plantapp_plantidentifier.listeners.OnTreeHistoryListener;
import com.wamessage.plantapp_plantidentifier.models.Tree;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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


public class HistoryTreeFragment extends Fragment implements OnTreeHistoryListener {
    private static final float IMAGE_MEAN = 0.0f;
    private static final float IMAGE_STD = 1.0f;
    private static final float PROBABILITY_MEAN = 0.0f;
    private static final float PROBABILITY_STD = 255.0f;

    HistoryTreeAdapter historyTreeAdapter;
    private int imageSizeX;
    private int imageSizeY;
    private TensorImage inputImageBuffer;
    LinearLayout linearLayout;
    private TensorBuffer outputProbabilityBuffer;
    private TensorProcessor probabilityProcessor;
    RecyclerView rcvHistoryPlant;

    protected Interpreter tfLite;
    List<Tree> trees;
    List<Tree> chosenTrees = new ArrayList();
    private boolean isInitializedBannerAd = false;

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_tree, container, false);
    }

    @Override 
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
        this.rcvHistoryPlant = (RecyclerView) view.findViewById(R.id.rcvHistoryPlant);
        Toolbar toolbar = (Toolbar) requireActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tree_history);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.HistoryTreeFragment$$ExternalSyntheticLambda0
            @Override 
            public final void onClick(View view2) {
                HistoryTreeFragment.this.m979xa931076e(view2);
            }
        });
        ((RelativeLayout) requireActivity().findViewById(R.id.relativeLayout)).setBackgroundResource(R.drawable.lightbg);
        ((LinearLayout) requireActivity().findViewById(R.id.linearLayoutNoRecord)).setVisibility(View.INVISIBLE);

        Bundle arguments = getArguments();
        if (!((Bundle) Objects.requireNonNull(arguments)).isEmpty()) {
            this.trees = (List) arguments.get("trees");
            HistoryTreeAdapter historyTreeAdapter = new HistoryTreeAdapter(this.trees, this);
            this.historyTreeAdapter = historyTreeAdapter;
            this.rcvHistoryPlant.setAdapter(historyTreeAdapter);
            this.rcvHistoryPlant.setLayoutManager(new LinearLayoutManager(requireActivity()));

        }
        loadTFLiteModel();

    }

    
    /* renamed from: lambda$onViewCreated$0$com-exorium-plant_recognition-fragment-HistoryTreeFragment  reason: not valid java name */
    public  void m979xa931076e(View view) {
        requireActivity().finish();
    }

    @Override 
    public void onResume() {

        super.onResume();
    }

    @Override 
    public void onStart() {
        super.onStart();

    }

    @Override 
    public void onPause() {
        super.onPause();

    }

    @Override // com.wamessage.plantapp_plantidentifier.listeners.OnTreeHistoryListener
    public void onItemHistoryFavorite(Tree tree) {
        tree.setFavorited(Boolean.valueOf(!tree.getFavorited().booleanValue()));
        AppDatabase.getInstance(requireActivity()).treeDao().updateTree(tree);
    }

    @Override // com.wamessage.plantapp_plantidentifier.listeners.OnTreeHistoryListener
    public void onItemHistoryDelete(final Tree tree) {
        final Dialog dialog = new Dialog(requireActivity());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.layout_dialog_delete);
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
        dialog.show();
        ((AppCompatButton) dialog.findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.HistoryTreeFragment$$ExternalSyntheticLambda1
            @Override 
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((AppCompatButton) dialog.findViewById(R.id.btnDelete)).setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.fragment.HistoryTreeFragment$$ExternalSyntheticLambda2
            @Override 
            public final void onClick(View view) {
                HistoryTreeFragment.this.m978x872e1343(tree, dialog, view);
            }
        });
    }

    
    /* renamed from: lambda$onItemHistoryDelete$2$com-exorium-plant_recognition-fragment-HistoryTreeFragment  reason: not valid java name */
    public  void m978x872e1343(Tree tree, Dialog dialog, View view) {
        handleDeleteItem(tree);
        dialog.dismiss();
    }

    private void handleDeleteItem(Tree tree) {
        int indexOf = this.trees.indexOf(tree);
        if (indexOf >= 0) {
            this.trees.remove(tree);
            AppDatabase.getInstance(requireActivity()).treeDao().deleteTree(tree);
            this.historyTreeAdapter.notifyItemRemoved(indexOf);

            if (this.trees.size() == 0) {
                this.linearLayout.setVisibility(View.INVISIBLE);
                ((RelativeLayout) requireActivity().findViewById(R.id.relativeLayout)).setBackgroundColor(getResources().getColor(R.color.white));
                ((LinearLayout) requireActivity().findViewById(R.id.linearLayoutNoRecord)).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override // com.wamessage.plantapp_plantidentifier.listeners.OnTreeHistoryListener
    public void onItemHistoryClick(Tree tree) {
        doRecognize(tree);
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
    private void doRecognize(Tree tree) {
        int[] shape = this.tfLite.getInputTensor(0).shape();
        this.imageSizeY = shape[1];
        this.imageSizeX = shape[2];
        DataType dataType = this.tfLite.getInputTensor(0).dataType();
        int[] shape2 = this.tfLite.getOutputTensor(0).shape();
        DataType dataType2 = this.tfLite.getOutputTensor(0).dataType();
        this.inputImageBuffer = new TensorImage(dataType);
        this.outputProbabilityBuffer = TensorBuffer.createFixedSize(shape2, dataType2);
        this.probabilityProcessor = new TensorProcessor.Builder().add(getPostProcessNormalizeOp()).build();
        if (tree.getImage() != null) {
            this.inputImageBuffer = loadImage(BitmapFactory.decodeByteArray(tree.getImage(), 0, tree.getImage().length));
        } else {
            this.inputImageBuffer = loadImage(((BitmapDrawable) getResources().getDrawable(R.drawable.icon_holder)).getBitmap());
        }
        this.tfLite.run(this.inputImageBuffer.getBuffer(), this.outputProbabilityBuffer.getBuffer().rewind());
        getResult(tree);
    }

    private TensorOperator getPostProcessNormalizeOp() {
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

    private void getResult(Tree tree) {
        try {
            List<String> loadLabels = FileUtil.loadLabels(requireActivity(), "plant_pro_label.txt");
            this.chosenTrees.clear();
            int i = 0;
            for (Map.Entry entry :  new TensorLabel(loadLabels, this.probabilityProcessor.process(this.outputProbabilityBuffer)).getMapWithFloatValue().entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toList())) {
                this.chosenTrees.add(new Tree((String) entry.getKey(), Double.valueOf(Double.parseDouble(((Float) entry.getValue()).toString()))));
                i++;
                if (i >= 10) {
                    break;
                }
            }
            openPlantIdentifierFragment(tree, this.chosenTrees);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPlantIdentifierFragment(Tree tree, List<Tree> trees) {
        TreeIdentifierFragment treeIdentifierFragment = new TreeIdentifierFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("treeBest", tree);
        bundle.putSerializable("trees", (Serializable) trees);
        bundle.putByteArray("img", tree.getImage());
        treeIdentifierFragment.setArguments(bundle);
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.addToBackStack(TreeIdentifierFragment.TAG);
        beginTransaction.replace(R.id.content_frame, treeIdentifierFragment);
        beginTransaction.commit();
    }
}
