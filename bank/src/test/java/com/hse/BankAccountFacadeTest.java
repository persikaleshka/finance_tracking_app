package com.hse;

import com.hse.domain.BankAccount;
import com.hse.facade.BankAccountFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountFacadeTest {
    private BankAccountFacade facade;

    @BeforeEach
    void setUp() {
        facade = new BankAccountFacade();
    }

    @Test
    void testCreateAccount() {
        String id = facade.createAccount("Test Account", 100.0);
        BankAccount account = facade.getAccountById(id);
        assertNotNull(account);
        assertEquals("Test Account", account.getName());
        assertEquals(100.0, account.getBalance());
    }
}
