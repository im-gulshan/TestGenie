package mainFrameworkUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampFormatter {
    public String fetchCurrentTimeStamp(){
        String format = "MM-dd-yyyy-hha-mm-ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
} //TimestampFormatter
