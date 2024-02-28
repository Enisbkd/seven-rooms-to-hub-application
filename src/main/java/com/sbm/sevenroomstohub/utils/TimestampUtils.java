package com.sbm.sevenroomstohub.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestampUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimestampUtils.class);

    public static LocalDateTime convertStringToTimestamp(String strDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(strDate);
        return dateTime;
    }
}
