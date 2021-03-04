package com.gamesys.cashier.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {

    private final String userName;
    private final String password;
    private final String dateOfBirth;
    private final String paymentNumber;
}
