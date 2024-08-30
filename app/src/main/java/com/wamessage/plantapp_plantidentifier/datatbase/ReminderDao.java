package com.wamessage.plantapp_plantidentifier.datatbase;

import com.wamessage.plantapp_plantidentifier.models.Reminder;
import java.util.List;


public interface ReminderDao {
    void deleteReminder(Reminder reminder);

    List<Reminder> getReminders(String type);

    long insertReminder(Reminder reminder);

    void updateReminder(Reminder reminder);
}
