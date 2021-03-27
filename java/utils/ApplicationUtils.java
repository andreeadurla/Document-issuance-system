package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationUtils {

    public static String printPrettyDate(Date date) {
        String pattern = "dd-MM-yyyy hh:mm a";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String prettyDate = dateFormat.format(date);

        return prettyDate;
    }


    private static String getYear(Date date) {
        String pattern = "yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        Date currentDate = new Date();
        String currentYear = dateFormat.format(currentDate);

        return currentYear;
    }

    private static Date convertFromStringToDate(String dateString) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return date;
    }

    public static Date getFirstDateOfCurrentYear() {
        String currentYear = getYear(new Date());
        return convertFromStringToDate("01-01-" + currentYear);
    }

    public static Date getLastDateOfCurrentYear() {
        String currentYear = getYear(new Date());
        return convertFromStringToDate("31-12-" + currentYear);
    }
}
