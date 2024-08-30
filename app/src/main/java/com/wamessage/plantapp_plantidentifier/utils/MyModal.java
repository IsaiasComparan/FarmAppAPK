package com.wamessage.plantapp_plantidentifier.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.wamessage.plantapp_plantidentifier.R;


public class MyModal extends Dialog {
    public MyModal(Context context) {
        super(context);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = Gravity.CENTER;
        getWindow().setAttributes(attributes);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setTitle((CharSequence) null);
        setCancelable(false);
        setOnCancelListener(null);
        setContentView(LayoutInflater.from(context).inflate(R.layout.process_modal, (ViewGroup) null));
    }
}
