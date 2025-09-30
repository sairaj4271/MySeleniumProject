package basicPrograms;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeZoneExample {
    public static void main(String[] args) {
        // Get current time in "Etc/UTC" time zone
        ZonedDateTime utcTime = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        
        // Subtract 5 minutes
        ZonedDateTime newTime = utcTime.minusMinutes(5);
        
        // Format the output in 12-hour format with AM/PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z");
        
        System.out.println("Original UTC Time: " + utcTime.format(formatter));
        System.out.println("Updated UTC Time (-5 min): " + newTime.format(formatter));
    }
}