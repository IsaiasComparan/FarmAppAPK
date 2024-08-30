package com.wamessage.plantapp_plantidentifier.models;




public class MyDay {
    private int idMyDay;
    private int namedDay;
    private int reminderID;
    private boolean selected;

    public int getIdMyDay() {
        return this.idMyDay;
    }

    public int getNamedDay() {
        return this.namedDay;
    }

    public int getReminderID() {
        return this.reminderID;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setIdMyDay(int id) {
        this.idMyDay = id;
    }

    public void setNamedDay(int namedDay) {
        this.namedDay = namedDay;
    }

    public void setReminderID(int reminderID) {
        this.reminderID = reminderID;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public MyDay() {
    }

    public MyDay(int id, int namedDay) {
        this.idMyDay = id;
        this.namedDay = namedDay;
        this.selected = false;
    }

    public String toString() {
        return "MyDay{namedDay=" + this.namedDay + ", selected=" + this.selected + '}';
    }
}
