package killian.brunet.sg.kata.models.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import killian.brunet.sg.kata.models.dto.request.OperationRequest;
import killian.brunet.sg.kata.models.enums.OperationType;
import killian.brunet.sg.kata.utils.DateHour;

@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long id_account;
    private LocalDateTime date;
    private Double value;
    private String description;
    private OperationType type;

    public Operation() {
    }

    public Operation(OperationRequest depositRequest, OperationType type) {
        this.id_account = depositRequest.getAccountId();
        this.value = depositRequest.getValue();
        this.description = depositRequest.getDescription();
        this.date = LocalDateTime.now();
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_account() {
        return id_account;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return DateHour.format(date);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }
}
