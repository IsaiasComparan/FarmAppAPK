package com.wamessage.plantapp_plantidentifier.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Reminder implements Serializable {
    private String alarmName;
    private Date alarmTime;
    private List<Integer> dayOfWeeks;
    private long id;
    private byte[] image;
    private String plantName;
    private String type;

    public String getAlarmName() {
        return this.alarmName;
    }

    public Date getAlarmTime() {
        return this.alarmTime;
    }

    public List<Integer> getDayOfWeeks() {
        return this.dayOfWeeks;
    }

    public long getId() {
        return this.id;
    }

    public byte[] getImage() {
        return this.image;
    }

    public String getPlantName() {
        return this.plantName;
    }

    public String getType() {
        return this.type;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setDayOfWeeks(List<Integer> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reminder() {
        this.id = 0L;
    }

    public Reminder(String plantName, String alarmName, Date alarmTime, List<Integer> dayOfWeeks, byte[] image) {
        this.id = 0L;
        this.plantName = plantName;
        this.alarmName = alarmName;
        this.alarmTime = alarmTime;
        this.dayOfWeeks = dayOfWeeks;
        this.image = image;
        this.type = "plant";
    }

    public Reminder(String plantName, String alarmName, Date alarmTime, List<Integer> dayOfWeeks, byte[] image, String type) {
        this.id = 0L;
        this.plantName = plantName;
        this.alarmName = alarmName;
        this.alarmTime = alarmTime;
        this.dayOfWeeks = dayOfWeeks;
        this.image = image;
        this.type = type;
    }
}
