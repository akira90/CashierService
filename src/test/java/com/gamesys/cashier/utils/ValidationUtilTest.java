package com.gamesys.cashier.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilTest {

    private static final String INVALID_ONE = "123456";
    private static final String INVALID_TWO = "202021";
    private static final String INVALID_THREE = "404808";

    private static final String[] INVALID_ISSUER_NUMBERS = {INVALID_ONE, INVALID_TWO, INVALID_THREE};

    @Test
    public void testIsDateISOFormatted() {
        assertTrue(ValidationUtil.isISOFormat("1980-01-21"));
        assertFalse(ValidationUtil.isISOFormat("01-10-21"));
    }

    @Test
    public void testIsIsoFormatWithNullOrEmptyValues() {
        assertFalse(ValidationUtil.isISOFormat(null));
        assertFalse(ValidationUtil.isISOFormat(""));
    }

    @Test
    public void testisValidUserNameWithAlphaNumericValues() {
        assertTrue(ValidationUtil.isValidUsername("AlphaUser123"));
    }

    @Test
    public void testisValidUserNameWithWhiteSpaceOrEmptyAndNullValues() {
        assertFalse(ValidationUtil.isValidUsername("AlphaUser123 space w hitespace"));
        assertFalse(ValidationUtil.isValidUsername(null));
        assertFalse(ValidationUtil.isValidUsername(""));
    }

    @Test
    public void testIfPasswordIsValid() {
        assertTrue(ValidationUtil.isValidPassword("Password123"));
    }

    @Test
    public void testIfPasswordIsInValid() {
        assertFalse(ValidationUtil.isValidPassword("pass"));
        assertFalse(ValidationUtil.isValidPassword(""));
        assertFalse(ValidationUtil.isValidPassword("password"));
        assertFalse(ValidationUtil.isValidPassword(null));
        assertFalse(ValidationUtil.isValidPassword("123!31349smlskad_"));
    }

    @Test
    public void testIfPaymentIssuerIdentificationNumberIsValid() {
        // 15 digits
        assertTrue(ValidationUtil.isPaymentNumberValid("349293081054422", INVALID_ISSUER_NUMBERS));
        // 16 digits
        assertTrue(ValidationUtil.isPaymentNumberValid("3492930810544221", INVALID_ISSUER_NUMBERS));
        // 17 digits
        assertTrue(ValidationUtil.isPaymentNumberValid("34929308105442212", INVALID_ISSUER_NUMBERS));
        // 18 digits
        assertTrue(ValidationUtil.isPaymentNumberValid("349293081054422123", INVALID_ISSUER_NUMBERS));
        // 19 digits
        assertTrue(ValidationUtil.isPaymentNumberValid("3492930810544221234", INVALID_ISSUER_NUMBERS));
    }

    @Test
    public void testIfPaymentIssuerIdentificationNumberIsInValid() {
        // 14 digits
        assertFalse(ValidationUtil.isPaymentNumberValid("34929308105442", INVALID_ISSUER_NUMBERS));
        assertFalse(ValidationUtil.isPaymentNumberValid(INVALID_ONE + "0810544251823", INVALID_ISSUER_NUMBERS));
        assertFalse(ValidationUtil.isPaymentNumberValid(INVALID_TWO + "928412i320901", INVALID_ISSUER_NUMBERS));
        assertFalse(ValidationUtil.isPaymentNumberValid(INVALID_THREE + "19375819419239", INVALID_ISSUER_NUMBERS));
        assertFalse(ValidationUtil.isPaymentNumberValid("abcedfghajeruti!", INVALID_ISSUER_NUMBERS));
        assertFalse(ValidationUtil.isPaymentNumberValid(null, INVALID_ISSUER_NUMBERS));
        assertFalse(ValidationUtil.isPaymentNumberValid(null, null));
    }
}