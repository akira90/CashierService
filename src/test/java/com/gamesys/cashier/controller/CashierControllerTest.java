package com.gamesys.cashier.controller;

import com.gamesys.cashier.model.Customer;
import com.gamesys.cashier.service.Cashier;
import com.gamesys.cashier.service.CashierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CashierControllerTest {

    private CashierController cashierController;
    private Cashier cashierService;

    @BeforeEach
    public void seUp() {
        cashierService = mock(CashierService.class);
        cashierController = new CashierController(cashierService);
    }

    @Test
    public void testRegisterEndpointReturnsStatusCode201() {
        var customer = Customer
                .builder()
                .userName("MadMax")
                .password("V8Fuel")
                .dateOfBirth("1956-03-01")
                .paymentNumber("349293081054422")
                .build();

        assertEquals(HttpStatus.CREATED.value(), cashierController.register(customer));
    }

    @Test
    public void testRegisterEndpointReturnsStatusCode400() {
        var customer = Customer
                .builder()
                .userName("invalid username!")
                .password("password")
                .dateOfBirth("10-01-20")
                .paymentNumber("3418")
                .build();

        assertEquals(HttpStatus.BAD_REQUEST.value(), cashierController.register(customer));
    }
}