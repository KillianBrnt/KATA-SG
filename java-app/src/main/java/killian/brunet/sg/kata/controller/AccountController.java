package killian.brunet.sg.kata.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import killian.brunet.sg.kata.models.dto.response.AccountReponse;
import killian.brunet.sg.kata.models.entity.Account;
import killian.brunet.sg.kata.models.service.AccountService;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> create() {
        Account account = accountService.create(new Account(LocalDateTime.now()));
        return new ResponseEntity<>(account.getId(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountReponse> get(@PathVariable("accountId") Long accountId) {
        Account account = accountService.getById(accountId);
        return new ResponseEntity<>(new AccountReponse(account.getId(), account.getCreatedAt(), account.getBalance()),
                HttpStatus.OK);
    }
}