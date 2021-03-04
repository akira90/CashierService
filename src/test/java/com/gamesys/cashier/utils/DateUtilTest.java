package com.gamesys.cashier.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateUtilTest {

    @Test
    public void testIsDateISOFormatted() {
        assertTrue(DateUtil.isISOFormat("1980-01-21"));
        assertFalse(DateUtil.isISOFormat("01-10-21"));
    }
}