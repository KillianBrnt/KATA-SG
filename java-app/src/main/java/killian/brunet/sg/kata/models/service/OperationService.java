package killian.brunet.sg.kata.models.service;

import java.util.List;

import killian.brunet.sg.kata.models.dto.request.OperationRequest;
import killian.brunet.sg.kata.models.dto.response.OperationResponse;
import killian.brunet.sg.kata.models.entity.Operation;

public interface OperationService {

    Operation create(Operation operation);

    List<Operation> getAccountOperations(Long id);

    Double getBalance(Long accountId);

    OperationResponse deposit(OperationRequest depositRequest);

    OperationResponse withdraw(OperationRequest withdrawRequest);

    List<Operation> getHistory(Long id);
}
