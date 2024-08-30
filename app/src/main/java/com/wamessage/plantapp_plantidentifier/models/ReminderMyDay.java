package com.wamessage.plantapp_plantidentifier.models;

import java.util.List;


public class ReminderMyDay {
    public List<MyDay> dayList;
    public Reminder reminder;

    public ReminderMyDay() {
    }

    public ReminderMyDay(Reminder reminder, List<MyDay> dayList) {
        this.reminder = reminder;
        this.dayList = dayList;
    }
}
