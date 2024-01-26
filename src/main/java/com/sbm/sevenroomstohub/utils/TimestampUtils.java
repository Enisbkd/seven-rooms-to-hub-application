package com.sbm.sevenroomstohub.utils;

import com.sbm.sevenroomstohub.service.impl.ClientPersistenceServiceImpl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
