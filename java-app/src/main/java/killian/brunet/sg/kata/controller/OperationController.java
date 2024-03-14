package killian.brunet.sg.kata.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import killian.brunet.sg.kata.models.dto.request.OperationRequest;
import killian.brunet.sg.kata.models.dto.response.OperationResponse;
import killian.brunet.sg.kata.models.entity.Operation;
import killian.brunet.sg.kata.models.service.OperationService;

@RestController
@RequestMapping(path = "/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping(value = "/balance/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@PathVariable("accountId") Integer accountId) {
        String balance = operationService.getBalance(Long.valueOf(accountId)).toString();
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationResponse> deposit(@RequestBody OperationRequest depositRequest) {
        OperationResponse depositResponse = operationService.deposit(depositRequest);
        return new ResponseEntity<>(depositResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OperationResponse> withdraw(@RequestBody OperationRequest withdrawRequest) {
        OperationResponse withdrawResponse = operationService.withdraw(withdrawRequest);
        return new ResponseEntity<>(withdrawResponse, HttpStatus.OK);
    }

    @GetMapping(value = "accountHistory/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Operation>> accountHistory(@PathVariable("accountId") Integer accountId) {
        List<Operation> operations = operationService.getAccountOperations(Long.valueOf(accountId));
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
}