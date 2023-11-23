package com.ouc.mallproduct.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeCasting {
    public static Date stringToData(String s) {
        Date date=null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
