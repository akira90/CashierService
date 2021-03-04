package com.gamesys.cashier.service;

import com.gamesys.cashier.exceptions.CustomerException;
import com.gamesys.cashier.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CashierService implements Cashier {

    private final Map<String, Customer> CUSTOMERS = new HashMap<>();

    @Override
    public void addCustomer(Customer customer) {
        CUSTOMERS.putIfAbsent(customer.getUserName(), customer);
    }

    @Override
    public Customer getCustomerByUserName(String userName) {
        return Optional.ofNullable(CUSTOMERS.get(userName)).orElseThrow(() -> new CustomerException("Customer does not exist. " + userName ));
    }

    @Override
    public boolean doesCustomerExist(String userName) {
        return CUSTOMERS.containsKey(userName);
    }
}
