package killian.brunet.sg.kata.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import killian.brunet.sg.kata.models.entity.Account;
import killian.brunet.sg.kata.models.service.AccountService;
import killian.brunet.sg.kata.repository.AccountRepository;

import org.springframework.transaction.annotation.Propagation;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (!optionalAccount.isPresent())
            throw new RuntimeException("Error: cannot find account");

        return optionalAccount.get();
    }

    @Override
    public Account create(Account account) {
        Assert.notNull(account, "Error: cannot create null account");

        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Assert.notNull(account, "Error: cannot resolve null account");

        return accountRepository.save(account);
    }
}
