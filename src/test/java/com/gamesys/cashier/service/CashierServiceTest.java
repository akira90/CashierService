package com.gamesys.cashier.service;

import com.gamesys.cashier.exceptions.CustomerException;
import com.gamesys.cashier.model.Customer;
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
        Customer customer = Customer
                .builder()
                .userName("MadMax")
                .build();
        cashierService.addCustomer(customer);
        Customer customerFromService = cashierService.getCustomerByUserName(customer.getUserName());

        assertEquals(customer, customerFromService);
    }

    @Test
    public void testIfCustomerExistsWithFalseUserName() {
        assertFalse(cashierService.doesCustomerExist("RandomUser"));
    }

    @Test
    public void testIfCustomerExistsWithExistingUser() {
        Customer customer = Customer
                .builder()
                .userName("CarlSagan")
                .build();
        cashierService.addCustomer(customer);

        assertTrue(cashierService.doesCustomerExist(customer.userName));
    }

    @Test
    public void testGetCustomerByUserNameWhenUserIsNotPresent() {
        assertThrows(CustomerException.class, () -> cashierService.getCustomerByUserName("ThisUserDoesNotExist"));
    }
}