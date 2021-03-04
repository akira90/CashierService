package com.gamesys.cashier.service;

import com.gamesys.cashier.model.Customer;

public interface Cashier {

    void addCustomer(Customer customer);
    Customer getCustomerByUserName(String userName);
    boolean doesCustomerExist(String userName);
}
