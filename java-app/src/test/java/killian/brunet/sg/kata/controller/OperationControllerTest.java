package killian.brunet.sg.kata.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import killian.brunet.sg.kata.models.dto.request.OperationRequest;
import killian.brunet.sg.kata.models.dto.response.OperationResponse;
import killian.brunet.sg.kata.models.entity.Operation;
import killian.brunet.sg.kata.models.enums.OperationType;
import killian.brunet.sg.kata.service.OperationServiceImpl;
import killian.brunet.sg.kata.utils.DateHour;

@WebMvcTest(OperationController.class)
public class OperationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OperationServiceImpl operationService;

    private Double value;
    private OperationRequest depositRequest;
    private OperationResponse depositResponse;
    private OperationRequest withdrawRequest;
    private OperationResponse withdrawResponse;
    private Operation operation;
    private List<Operation> operations;

    @BeforeEach
    public void setUp() {
        value = 200.00d;
        depositRequest = new OperationRequest(1L, 1000d, "Deposit test");
        depositResponse = new OperationResponse(1000d, DateHour.format(LocalDateTime.now()), OperationType.DEPOSIT);
        withdrawRequest = new OperationRequest(1L, -1000d, "Deposit test");
        withdrawResponse = new OperationResponse(-1000d, DateHour.format(LocalDateTime.now()), OperationType.WITHDRAW);
        operation = new Operation(depositRequest, OperationType.DEPOSIT);
        operations = new ArrayList<>();
        operations.add(operation);
    }

    @Test
    public void balanceTest() throws Exception {
        when(operationService.getBalance(1L)).thenReturn(value);
        mvc.perform(get("/operation/balance/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void depositTest() throws Exception {
        when(operationService.deposit(any(OperationRequest.class))).thenReturn(depositResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/operation/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(depositRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void withdrawTest() throws Exception {
        when(operationService.withdraw(any(OperationRequest.class))).thenReturn(withdrawResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/operation/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(withdrawRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void accountHistoryTest() throws Exception {
        when(operationService.getAccountOperations(1L)).thenReturn(operations);
        mvc.perform(get("/operation/accountHistory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}