package com.gamesys.cashier.controller;

import com.gamesys.cashier.model.Customer;
import com.gamesys.cashier.service.Cashier;
import com.gamesys.cashier.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CashierController {

    private final Cashier cashierService;

    @Autowired
    public CashierController(Cashier cashierService) {
        this.cashierService = cashierService;
    }

    @PostMapping("/register")
    public Integer register(@RequestBody Customer customer) {
        if (!ValidationUtil.isValidUsername(customer.getUserName()) &&
            !ValidationUtil.isValidPassword(customer.getPassword()) &&
            !ValidationUtil.isISOFormat(customer.getDateOfBirth()) &&
            !ValidationUtil.isPaymentNumberValid(customer.getPaymentNumber(), new String[]{"202021"})
        ) return new ResponseEntity<>(HttpStatus.BAD_REQUEST).getStatusCodeValue();
        return new ResponseEntity<>(customer, HttpStatus.CREATED).getStatusCodeValue();
    }
}
