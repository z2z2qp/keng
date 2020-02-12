package code.java;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTest{

    public static LocalDateTime str2DateTime(String datetime){
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String dateTime2Str(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static Long dateTime2Long(LocalDateTime dateTime){
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    public LocalDateTime long2LocalDateTime(Long datetime){
        return LocalDateTime.ofEpochSecond(datetime/1000, 0, ZoneOffset.ofHours(8));
    }
}