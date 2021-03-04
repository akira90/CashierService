package com.gamesys.cashier.model;

import lombok.Builder;

@Builder
public class Customer {

    public String userName;
    public String password;
    public String dateOfBirth;
    public String paymentNumber;
}
