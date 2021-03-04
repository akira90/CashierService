package com.gamesys.cashier.utils;


import java.util.Optional;

public class DateUtil {

    /**
     * Checks if Date is of ISO 8601 format
     * @param date Date as String not Date Object
     * @return boolean true or false
     */
    public static boolean isISOFormat(String date) {
        return (Optional.ofNullable(date).isPresent() ? date : "").matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
