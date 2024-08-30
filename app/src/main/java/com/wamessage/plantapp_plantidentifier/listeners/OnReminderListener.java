package com.wamessage.plantapp_plantidentifier.listeners;

import com.wamessage.plantapp_plantidentifier.models.Reminder;


public interface OnReminderListener {
    void onReminderDelete(Reminder reminder);

    void onReminderUpdate(Reminder reminder);
}
