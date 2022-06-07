package com.joseluis.crowfundingapp.database;



import java.util.Date;

public class StringToDateConverter {

    public static Date stringToDate(String value) {

        int year = Integer.parseInt(value.substring(0, 4)) - 1900;
        int month = Integer.parseInt(value.substring(5, 7)) - 1;
        int day = Integer.parseInt(value.substring(8, 10));
        int hour = Integer.parseInt(value.substring(11, 13));
        int min = Integer.parseInt(value.substring(14, 16));
        int seg = Integer.parseInt(value.substring(17, 19));

        return new Date(year, month, day, hour, min, seg);
    }


    public static String dateToString(Date date) {
        String year = Integer.toString(date.getYear() + 1900);

        String month = "";
        if (date.getMonth() < 9) {//mes representado con una cifra
            month = "0";
        }
        month = month + Integer.toString(date.getMonth() + 1);

        String day = "";
        if (date.getDay() < 10) {
            day = "0";
        }
        day = day + Integer.toString(date.getDay());

        String hour = "";
        if (date.getHours() < 10) {
            hour = "0";
        }
        hour = hour + Integer.toString(date.getHours());

        String min = "";
        if (date.getMinutes() < 10) {
            min = "0";
        }
        min = min + Integer.toString(date.getMinutes());

        String seg = "";
        if (date.getSeconds() < 10) {
            seg = "0";
        }
        seg = seg + Integer.toString(date.getSeconds());

        return year+"/"+month+"/"+day+" "+hour+":"+min+":"+seg;
    }
}
