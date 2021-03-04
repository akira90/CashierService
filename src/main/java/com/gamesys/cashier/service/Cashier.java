package com.gamesys.cashier.service;

import com.gamesys.cashier.model.User;

public interface Cashier {

    void addCustomer(User user);
    User getCustomerByUserName(String userName);
    boolean doesCustomerExist(String userName);
}
