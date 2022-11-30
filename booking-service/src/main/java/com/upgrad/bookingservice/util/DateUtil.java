package com.upgrad.bookingservice.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;


public class DateUtil {
    public static DateTimeFormatter dateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static Date getDate(String date) {
        if (date == null) {
            return null;
        }
        Date parseDate = null;
        Calendar cal = Calendar.getInstance();
        Boolean flag = Boolean.FALSE;
        try {
            parseDate = dateFormat.parseDateTime(date).toDate();
        } catch (IllegalArgumentException e) {
            ;
        }
        if (null == parseDate) {
            return new Date();
        }
        cal.setTime(parseDate);
        return cal.getTime();
        
    }
    public static Long daysDiff(Date from, Date to) {
        return daysDiff(from.getTime(), to.getTime());
    }

    public static long daysDiff(long from, long to) {
        Double d = (Math.ceil((to - from) / 86400000D)); // 1000 * 60 * 60 * 24
        return d.intValue();
    }
}
