package killian.brunet.sg.kata.models.dto.response;

import java.time.LocalDateTime;

import killian.brunet.sg.kata.utils.DateHour;

public class AccountReponse {

    private Long id;
    private String createdAt;
    private Double balance;

    public AccountReponse(Long id, LocalDateTime createdAt, Double balance) {
        this.id = id;
        this.createdAt =  DateHour.format(createdAt);
        this.balance = balance;
    }

    public AccountReponse(Long id, String createdAt, Double balance) {
        this.id = id;
        this.createdAt =  createdAt;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt =  DateHour.format(createdAt);
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt =  createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
