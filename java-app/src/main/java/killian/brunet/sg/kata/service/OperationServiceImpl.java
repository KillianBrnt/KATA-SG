package killian.brunet.sg.kata.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import killian.brunet.sg.kata.models.dto.request.OperationRequest;
import killian.brunet.sg.kata.models.dto.response.OperationResponse;
import killian.brunet.sg.kata.models.entity.Account;
import killian.brunet.sg.kata.models.entity.Operation;
import killian.brunet.sg.kata.models.enums.OperationType;
import killian.brunet.sg.kata.models.service.AccountService;
import killian.brunet.sg.kata.models.service.OperationService;
import killian.brunet.sg.kata.repository.OperationRepository;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OperationServiceImpl implements OperationService {

    @Autowired
    AccountService accountService;

    @Autowired
    OperationRepository operationRepository;

    @Override
    public Double getBalance(Long accountId) {
        Account account = accountService.getById(accountId);
        return account.getBalance();
    }

    @Override
    public Operation create(Operation operation) {
        Assert.notNull(operation, "Error: cannot save null operation");

        return operationRepository.save(operation);
    }

    @Override
    public List<Operation> getAccountOperations(Long id) {
        return operationRepository.findAccountOperations(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OperationResponse deposit(OperationRequest depositRequest) {
        Account account = accountService.getById(depositRequest.getAccountId());

        this.validDepositValue(account, depositRequest.getValue());

        account.setBalance(account.getBalance() + depositRequest.getValue());
        Operation deposit = this.create(new Operation(depositRequest, OperationType.DEPOSIT));
        accountService.update(account);

        return new OperationResponse(deposit.getValue(), deposit.getDate(), OperationType.DEPOSIT);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public OperationResponse withdraw(OperationRequest withdrawRequest) {
        Account account = accountService.getById(withdrawRequest.getAccountId());

        this.validWithdrawValue(account, withdrawRequest.getValue());

        account.setBalance(account.getBalance() + withdrawRequest.getValue());
        Operation withdraw = this.create(new Operation(withdrawRequest, OperationType.DEPOSIT));
        accountService.update(account);

        return new OperationResponse(withdraw.getValue(), withdraw.getDate(), OperationType.WITHDRAW);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Operation> getHistory(Long accountId) {
        List<Operation> operations = this.getAccountOperations(accountId);
        return operations;
    }

    private void validWithdrawValue(Account account, Double value) {
        if (value > 0)
            throw new RuntimeException("Error: cannot withdraw positive value");
        if (account.getBalance() < value)
            throw new RuntimeException("Error: cannot withdraw more than account balance");
    }

    private void validDepositValue(Account account, Double value) {
        if (value < 0)
            throw new RuntimeException("Error: cannot deposit negative value");
    }

}
