package com.wamessage.plantapp_plantidentifier.utils;


public class ConvertUtils {
    public static double convertFloatToPercent(double score) {
        return score > 1.0d ? score : Math.floor((score * 100.0d) * 100.0d) / 100.0d;
    }
}
