package com.wamessage.plantapp_plantidentifier.utils;

import java.util.Date;


public class DateTypeConverter {
    public Date toDate(Long timestamp) {
        return new Date(timestamp.longValue());
    }

    public Long toTimestamp(Date date) {
        return Long.valueOf(date.getTime());
    }
}
