package basicPrograms;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ESTOnlyTime {
    public static void main(String[] args) {
        // Get current time in America/New_York (EST or EDT depending on the date)
        ZonedDateTime nyTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        
        // Subtract 5 minutes
        ZonedDateTime newTime = nyTime.minusMinutes(5);
        
        // Format the output in 12-hour format with AM/PM and zone abbreviation
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a z");

        System.out.println("Original New York Time: " + nyTime.format(formatter));
        System.out.println("Updated New York Time (-5 min): " + newTime.format(formatter));
    }
}
