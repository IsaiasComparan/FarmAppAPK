package com.wamessage.plantapp_plantidentifier.utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class IntegerListConverter {
    private static final String DELIMITER = ",";

    public static String fromIntegerList(List<Integer> integerList) {
        if (integerList == null || integerList.isEmpty()) {
            return null;
        }
        return (String) integerList.stream().map(new Function() { 
            @Override
            public final Object apply(Object obj) {
                String obj2;
                obj2 = ((Integer) obj).toString();
                return obj2;
            }
        }).collect(Collectors.joining(DELIMITER));
    }

    public static List<Integer> toIntegerList(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        return (List) Arrays.stream(data.split(DELIMITER)).map(new Function() { 
            @Override 
            public final Object apply(Object obj) {
                int parseInt;
                parseInt = Integer.parseInt((String) obj);
                return Integer.valueOf(parseInt);
            }
        }).collect(Collectors.toList());
    }
}
