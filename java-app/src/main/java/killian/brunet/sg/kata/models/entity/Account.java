package killian.brunet.sg.kata.models.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import killian.brunet.sg.kata.utils.DateHour;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE_CREATION")
    private LocalDateTime createdAt;

    private Double balance;

    public Account() {
    }

    public Account(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.balance = 0d;
    }

    public Account(LocalDateTime createdAt, Double balance) {
        this.createdAt = createdAt;
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
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return DateHour.format(createdAt);
    }
}
