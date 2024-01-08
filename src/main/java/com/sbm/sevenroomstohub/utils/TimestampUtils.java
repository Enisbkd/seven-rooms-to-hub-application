package com.sbm.sevenroomstohub.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampUtils {

    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            //2019-09-13T17:28:03.813917
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ");
            // you can change format of date
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }
}
