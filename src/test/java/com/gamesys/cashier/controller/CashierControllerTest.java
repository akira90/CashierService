package com.gamesys.cashier.controller;

import com.gamesys.cashier.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashierControllerTest {

    private CashierController cashierController;

    @BeforeEach
    public void seUp() {
        cashierController = new CashierController();
    }

    @Test
    public void testRegisterEndpointReturnsStatusCode201() {
        Customer customer = Customer
                .builder()
                .userName("MadMax")
                .password("V8Fuel")
                .dateOfBirth("1956-03-01")
                .paymentNumber("349293081054422")
                .build();
        assertEquals(HttpStatus.CREATED.value(), cashierController.register(customer));
    }
}