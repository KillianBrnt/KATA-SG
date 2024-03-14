package killian.brunet.sg.kata.models.service;

import killian.brunet.sg.kata.models.entity.Account;

public interface AccountService {
    
    Account getById(Long id);

    Account create(Account newAccount);

    Account update(Account account);
}
