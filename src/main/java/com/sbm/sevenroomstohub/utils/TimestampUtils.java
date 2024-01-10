package com.sbm.sevenroomstohub.utils;

import com.sbm.sevenroomstohub.service.impl.ClientPersistenceServiceImpl;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestampUtils {

    private static final Logger logger = LoggerFactory.getLogger(TimestampUtils.class);

    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            Date date = formatter.parse(strDate);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
        } catch (ParseException e) {
            logger.debug("Exception :" + e);
            return null;
        }
    }
}
