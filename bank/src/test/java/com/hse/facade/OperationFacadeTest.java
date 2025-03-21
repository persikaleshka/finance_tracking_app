package com.hse.facade;

import com.hse.domain.Operation;
import com.hse.domain.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OperationFacadeTest {
    private OperationFacade facade;
    private BankAccountFacade accountFacade;

    @BeforeEach
    void setUp() {
        accountFacade = new BankAccountFacade();
        facade = new OperationFacade(accountFacade);
    }

    @Test
    void testAddOperation() {
        String accountId = accountFacade.createAccount("Test Account", 500.0);
        String operationId = facade.addOperation(OperationType.EXPENSE, accountId, 50.0, LocalDate.now(), "Dinner", "Food");

        Operation operation = facade.getOperationById(operationId);
        assertNotNull(operation);
        assertEquals(OperationType.EXPENSE, operation.getType());
        assertEquals(50.0, operation.getAmount());
    }

    @Test
    void testDeleteOperation() {
        String accountId = accountFacade.createAccount("Test Account", 1000.0);
        String operationId = facade.addOperation(OperationType.INCOME, accountId, 200.0, LocalDate.now(), "Salary", "Job");

        facade.deleteOperation(operationId);
        assertThrows(IllegalArgumentException.class, () -> facade.getOperationById(operationId));
    }

    @Test
    void testGetOperationsByAccount() {
        String accountId = accountFacade.createAccount("Test Account", 500.0);
        facade.addOperation(OperationType.EXPENSE, accountId, 100.0, LocalDate.now(), "Shopping", "Groceries");
        facade.addOperation(OperationType.INCOME, accountId, 200.0, LocalDate.now(), "Freelance", "Job");

        List<Operation> operations = facade.getOperationsByAccount(accountId);
        assertEquals(2, operations.size());
    }
}
