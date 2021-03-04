package com.gamesys.cashier.controller;

import com.gamesys.cashier.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CashierController {

    @PostMapping("/register")
    public Integer register(@RequestBody Customer customer) {
        return new ResponseEntity<>(customer, HttpStatus.CREATED).getStatusCodeValue();
    }
}
