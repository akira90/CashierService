package com.gamesys.cashier.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamesys.cashier.model.User;
import com.gamesys.cashier.service.CashierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CashierControllerTest {

    private CashierController cashierController;
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void seUp() {
        CashierService cashierService = mock(CashierService.class);
        cashierController = new CashierController(cashierService);
        mapper = new ObjectMapper();
    }

    @Test
    public void testRegisterEndpointReturnsStatusCode201() throws Exception {
        var user = User
                .builder()
                .userName("MadMax")
                .password("V8Fuel")
                .dateOfBirth("1956-03-01")
                .paymentNumber("349293081054422")
                .build();

        mockMvc.perform(post("/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertEquals(user, cashierController.register(user));

    }

    @Test
    public void testRegisterEndpointReturnsStatusCode400() throws Exception {
        var user = User
                .builder()
                .userName("invalid username!")
                .password("password")
                .dateOfBirth("10-01-20")
                .paymentNumber("3418")
                .build();

        mockMvc.perform(post("/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

    @Test
    public void testRegisterEndpointReturnsStatusCode403ForAgeRestriction() throws Exception {
        var user = User
                .builder()
                .userName("MadMax")
                .password("V8Fuel")
                .dateOfBirth("2004-03-01")
                .paymentNumber("349293081054422")
                .build();

        mockMvc.perform(post("/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

    @Test
    public void testIfPaymentIssuerNumberIsAllowedAndReturnHttpStatus406() throws Exception {
        var user = User
                .builder()
                .userName("PaymentTest")
                .password("Grandslam11")
                .dateOfBirth("1956-06-06")
                .paymentNumber("202021081054422")
                .build();

        mockMvc.perform(post("/register")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotAcceptable())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));
    }

}