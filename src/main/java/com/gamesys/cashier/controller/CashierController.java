package com.gamesys.cashier.controller;

import com.gamesys.cashier.model.User;
import com.gamesys.cashier.service.CashierService;
import com.gamesys.cashier.utils.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class CashierController {

    private final CashierService cashierService;

    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User Created")
    public User register(@RequestBody User user) {
        if (hasValidFields(user))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST);

        if (!ValidationUtil.isAboveAgeRequirement(user.getDateOfBirth()))
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "User is under aged.");

        if (cashierService.doesCustomerExist(user.getUserName()))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    user.getUserName() + " already exists.");

        if (!ValidationUtil.isPaymentNumberValid(user.getPaymentNumber(), new String[]{"202021"}))
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE,
                    "Invalid payment issuer number starting with 202021");

        cashierService.addCustomer(user);
        return user;
    }

    private boolean hasValidFields(User user) {
        return !ValidationUtil.isValidUsername(user.getUserName()) &&
                !ValidationUtil.isValidPassword(user.getPassword()) &&
                !ValidationUtil.isISOFormat(user.getDateOfBirth()) &&
                !ValidationUtil.hasPaymentValidLength(user.getPaymentNumber());
    }


}
