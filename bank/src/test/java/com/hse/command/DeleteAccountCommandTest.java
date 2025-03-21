package com.hse.command;

import com.hse.facade.BankAccountFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class DeleteAccountCommandTest {
    private BankAccountFacade facade;
    private DeleteAccountCommand command;

    @BeforeEach
    void setUp() {
        facade = mock(BankAccountFacade.class);
        command = new DeleteAccountCommand(facade, "acc1");
    }

    @Test
    void testExecute() {
        command.execute();
        verify(facade, times(1)).deleteAccount("acc1");
    }
}
