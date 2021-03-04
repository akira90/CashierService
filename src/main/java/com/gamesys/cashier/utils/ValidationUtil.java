package com.gamesys.cashier.utils;

import com.gamesys.cashier.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

public class ValidationUtil {

    public static final String ISO_DATE_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}";
    public static final String ISSUER_NUMBER_REGEX = "^(?=.*[0-9]).{15,19}$";
    private static final String EMPTY_STRING = "";

    /**
     * Indicate whether Date is of ISO 8601 format.
     *
     * @param date Date as String not Date Object
     * @return true if it matches the date is of ISO-8601 format otherwise false.
     */
    public static boolean isISOFormat(String date) {
        return (Optional
                .ofNullable(date)
                .isPresent() ? date : EMPTY_STRING)
                .matches(ISO_DATE_REGEX);
    }

    /**
     * Indicate whether userName has only a alphanumeric value.
     *
     * @param userName user name
     * @return true if it matches the userName constraint otherwise false
     */
    public static boolean isValidUsername(String userName) {
        return (Optional
                .ofNullable(userName)
                .isPresent() ? userName : EMPTY_STRING)
                .matches(ALPHANUMERIC_REGEX);
    }

    /**
     * Indicate whether password field is min length 4, at least one upper case letter & number.
     *
     * @param password password
     * @return true if it matches the password constraint otherwise false.
     */
    public static boolean isValidPassword(String password) {
        return (Optional
                .ofNullable(password)
                .isPresent() ? password : EMPTY_STRING)
                .matches(PASSWORD_REGEX);
    }

    /**
     * Indicate whether payment issuer identification number has length between 15 and 19.
     *
     * @param paymentNumber String of numbers ranging between 15 and 19 digits
     * @return true if paymentNumber is between 15 and 19 digits.
     */
    public static boolean hasPaymentValidLength(String paymentNumber) {
        return (Optional
                .ofNullable(paymentNumber)
                .isPresent() && paymentNumber.length() > 6 ? paymentNumber : "123456")
                .matches(ISSUER_NUMBER_REGEX);
    }

    /**
     * Indicate whether payment issuer identification number is valid against a set of numbers.
     *
     * @param paymentNumber String of numbers ranging between 15 and 19 digits
     * @return true if paymentNumber is between 15 and 19 digits and does not match the list of numbers in the parameter.
     */
    public static boolean isPaymentNumberValid(String paymentNumber, String[] invalidIssuerNumber) {
        final var PAYMENT_FROM_OPTIONAL = Optional.ofNullable(paymentNumber).isPresent() && paymentNumber.length() > 6 ? paymentNumber : "123456";
        final var INVALID_ISSUER_NO_FROM_OPTIONAL = Optional.ofNullable(invalidIssuerNumber).isPresent() ? invalidIssuerNumber : new String[]{};
        final var SIX_DIGITS_FROM_PAYMENT = PAYMENT_FROM_OPTIONAL.substring(0, 6);
        var isValidIssuerNumber = Arrays.stream(INVALID_ISSUER_NO_FROM_OPTIONAL).noneMatch(SIX_DIGITS_FROM_PAYMENT::contains);
        var isValidLength = PAYMENT_FROM_OPTIONAL.matches(ISSUER_NUMBER_REGEX);
        return isValidIssuerNumber && isValidLength;
    }

    /**
     * Check whether the customer is 18 or above.
     *
     * @param dateOfBirth String date of birth in yyyy-MM-dd format
     * @return true if the customer is 18 or above, false otherwise.
     */
    public static boolean isAboveAgeRequirement(String dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.equals(EMPTY_STRING) || !isISOFormat(dateOfBirth)) return false;
        return Period.between(LocalDate.parse(dateOfBirth), LocalDate.now()).getYears() >= 18;
    }


    public static boolean hasValidFields(User user) {
        return !isValidUsername(user.getUserName()) &&
                !isValidPassword(user.getPassword()) &&
                !isISOFormat(user.getDateOfBirth()) &&
                !hasPaymentValidLength(user.getPaymentNumber());
    }
}
