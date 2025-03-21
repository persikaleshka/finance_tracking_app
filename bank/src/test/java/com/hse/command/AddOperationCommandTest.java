package com.hse.command;

import com.hse.domain.OperationType;
import com.hse.facade.OperationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

class AddOperationCommandTest {
    private OperationFacade operationFacade;
    private AddOperationCommand command;

    @BeforeEach
    void setUp() {
        operationFacade = mock(OperationFacade.class);
        command = new AddOperationCommand(operationFacade, OperationType.INCOME, "acc1", 100.0, LocalDate.now(), "Salary", "Job");
    }

    @Test
    void testExecute() {
        command.execute();
        verify(operationFacade, times(1)).addOperation(any(), any(), anyDouble(), any(), any(), any());
    }
}
