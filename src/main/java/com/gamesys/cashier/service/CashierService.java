package com.gamesys.cashier.service;

import com.gamesys.cashier.exceptions.CustomerException;
import com.gamesys.cashier.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CashierService implements Cashier {

    // This Map functions as the CashierRepo - as a means of persistence.
    private final Map<String, User> customers = new HashMap<>();

    @Override
    public void addCustomer(User user) {
        customers.putIfAbsent(user.getUserName(), user);
    }

    @Override
    public User getCustomerByUserName(String userName) {
        return Optional.ofNullable(customers.get(userName)).orElseThrow(() -> new CustomerException("Customer does not exist. " + userName ));
    }

    @Override
    public boolean doesCustomerExist(String userName) {
        return customers.containsKey(userName);
    }
}
