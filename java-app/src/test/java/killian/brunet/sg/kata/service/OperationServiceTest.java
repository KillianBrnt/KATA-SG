package killian.brunet.sg.kata.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import killian.brunet.sg.kata.models.dto.request.OperationRequest;
import killian.brunet.sg.kata.models.entity.Account;
import killian.brunet.sg.kata.models.entity.Operation;
import killian.brunet.sg.kata.models.enums.OperationType;
import killian.brunet.sg.kata.models.service.AccountService;
import killian.brunet.sg.kata.repository.OperationRepository;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private OperationServiceImpl service;

    private Account account;
    private OperationRequest depositRequest;
    private OperationRequest withdrawRequest;
    private Operation operation1;
    private Operation operation2;
    private List<Operation> operations;

    @BeforeEach
    public void setUp() {
        account = new Account(LocalDateTime.now(), 1000d);
        account.setId(1L);
        depositRequest = new OperationRequest(1L, 1000d, "Deposit test");
        withdrawRequest = new OperationRequest(1L, -1000d, "Deposit test");
        operation1 = new Operation(depositRequest, OperationType.DEPOSIT);
        operation2 = new Operation(withdrawRequest, OperationType.WITHDRAW);
        operations = new ArrayList<>();
        operations.add(operation1);
        operations.add(operation2);
    }

    @Test
    public void createOperationTest() {
        when(operationRepository.save(any(Operation.class))).thenReturn(operation1);

        Operation op = service.create(operation1);
        assertEquals(operation1.getId(), op.getId());
        assertEquals(operation1.getDate(), op.getDate());
        assertEquals(operation1.getValue(), op.getValue(), 0);
        assertEquals(operation1.getDescription(), op.getDescription());
        assertEquals(operation1.getId_account(), op.getId_account());
    }

    @Test
    public void getAccountOperationsTest() {
        when(operationRepository.findAccountOperations(1L)).thenReturn(operations);
        List<Operation> list = service.getAccountOperations(1L);
        assertEquals(2, list.size());
    }

    @Test
    public void getBalanceTest() {
        when(accountService.getById(1L)).thenReturn(account);

        Double resp = service.getBalance(1L);
        assertEquals(1000d, resp);
    }

    @Test
    public void depositTest() {
        when(accountService.getById(1L)).thenReturn(account);
        when(operationRepository.save(any(Operation.class))).thenReturn(operation1);
        assertNotNull(service.deposit(depositRequest));
    }

    @Test
    public void withdrawTest() {
        when(accountService.getById(1L)).thenReturn(account);
        when(operationRepository.save(any(Operation.class))).thenReturn(operation2);
        assertNotNull(service.withdraw(withdrawRequest));
    }

    @Test
    public void getTransfersTest_WhenPassedValidDataReturnListTransactions() {
        when(operationRepository.findAccountOperations(1L)).thenReturn(operations);
        List<Operation> list = service.getHistory(1L);
        assertEquals(2, list.size());
    }
}
