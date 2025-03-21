package com.hse.exceptions;

import com.hse.facade.BankAccountFacade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {
    @Test
    void testAccountNotFoundException() {
        BankAccountFacade facade = new BankAccountFacade();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            facade.getAccountById("non-existent-id");
        });

        assertTrue(exception.getMessage().contains("Account not found"));
    }
}