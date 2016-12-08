package com.staff.personal.data;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nazar on 06.12.16.
 */
@Slf4j
public class DateUtil {

   /* private static DateUtil instance = null;

    private DateUtil() {

    }

    public static synchronized DateUtil getInstance() {
        if (instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");


    public Date parseDate(String date) {
        log.info("in parseDate with date" + date);
        Date date1;
        if (date == null) {
            return new Date();
        }
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            date1 = simpleDateFormatNew.parse(date);
        }
        return date1;
    }

    public String formatDate(Date date) {


        return "";
    }*/
}
