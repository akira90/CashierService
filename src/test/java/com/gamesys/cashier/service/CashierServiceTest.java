package com.gamesys.cashier.service;

import com.gamesys.cashier.exceptions.CustomerException;
import com.gamesys.cashier.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashierServiceTest {

    private Cashier cashierService;

    @BeforeEach
    public void setUp() {
        cashierService = new CashierService();
    }

    @Test
    public void testAddCustomerToRepoIfCustomerDoesNotExist() {
        var customer = User
                .builder()
                .userName("MadMax")
                .build();
        cashierService.addCustomer(customer);
        var customerFromRepo = cashierService.getCustomerByUserName(customer.getUserName());

        assertEquals(customer, customerFromRepo);
    }

    @Test
    public void testIfCustomerExistsWithFalseUserName() {
        assertFalse(cashierService.doesCustomerExist("RandomUser"));
    }

    @Test
    public void testIfCustomerExistsWithExistingUser() {
        var customer = User
                .builder()
                .userName("CarlSagan")
                .build();
        cashierService.addCustomer(customer);

        assertTrue(cashierService.doesCustomerExist("CarlSagan"));
    }

    @Test
    public void testGetCustomerByUserNameWhenUserIsNotPresent() {
        assertThrows(CustomerException.class, () -> cashierService.getCustomerByUserName("ThisUserDoesNotExist"));
    }

    @Test
    public void testGetCustomerByUserNameWhenUserIsPresent() {
        var customer = User
                .builder()
                .userName("IsaacNewton")
                .build();
        cashierService.addCustomer(customer);
        var customerFromRepo = cashierService.getCustomerByUserName("IsaacNewton");

        assertEquals(customer, customerFromRepo);
    }
}