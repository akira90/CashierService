package com.gamesys.cashier.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {

    public String userName;
    public String password;
    public String dateOfBirth;
    public String paymentNumber;
}
