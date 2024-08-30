package com.wamessage.plantapp_plantidentifier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.wamessage.plantapp_plantidentifier.R;
import com.wamessage.plantapp_plantidentifier.listeners.OnDayOfWeekListener;
import com.wamessage.plantapp_plantidentifier.models.MyDay;
import java.util.ArrayList;


public class DayOfWeekAdapter extends ArrayAdapter<MyDay> {
    ArrayList<MyDay> arrayList;
    Context context;
    OnDayOfWeekListener onDayOfWeekListener;

    public ArrayList<MyDay> getDayofWeekList() {
        return this.arrayList;
    }

    public DayOfWeekAdapter(Context context, ArrayList<MyDay> arrayList, OnDayOfWeekListener onDayOfWeekListener) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        this.onDayOfWeekListener = onDayOfWeekListener;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_day_of_week, parent, false);
        }
        final MyDay item = getItem(position);
        final TextView textView = (TextView) convertView.findViewById(R.id.textViewDayOfWeek);
        switch (item.getNamedDay()) {
            case 1:
                textView.setText(this.context.getResources().getString(R.string.sunday));
                break;
            case 2:
                textView.setText(this.context.getResources().getString(R.string.monday));
                break;
            case 3:
                textView.setText(this.context.getResources().getString(R.string.tuesday));
                break;
            case 4:
                textView.setText(this.context.getResources().getString(R.string.wednesday));
                break;
            case 5:
                textView.setText(this.context.getResources().getString(R.string.thusday));
                break;
            case 6:
                textView.setText(this.context.getResources().getString(R.string.friday));
                break;
            case 7:
                textView.setText(this.context.getResources().getString(R.string.saturday));
                break;
        }
        if (!item.isSelected()) {
            textView.setBackgroundResource(R.drawable.item_selector_remind);
            textView.setTextColor(parent.getResources().getColor(R.color.black));
        } else {
            textView.setBackgroundResource(R.drawable.item_selector_remind_active);
            textView.setTextColor(parent.getResources().getColor(R.color.white));
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.wamessage.plantapp_plantidentifier.adapter.DayOfWeekAdapter.1
            @Override 
            public void onClick(View v) {
                if (!item.isSelected()) {
                    item.setSelected(true);
                    textView.setBackgroundResource(R.drawable.item_selector_remind_active);
                    textView.setTextColor(parent.getResources().getColor(R.color.white));
                } else {
                    item.setSelected(false);
                    textView.setBackgroundResource(R.drawable.item_selector_remind);
                    textView.setTextColor(parent.getResources().getColor(R.color.black));
                }
                DayOfWeekAdapter.this.notifyDataSetChanged();
                DayOfWeekAdapter.this.onDayOfWeekListener.onDayOfWeekClick(item);
            }
        });
        return convertView;
    }
}
