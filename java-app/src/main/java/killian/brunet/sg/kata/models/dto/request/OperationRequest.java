package killian.brunet.sg.kata.models.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class OperationRequest {
    @NotBlank(message = "AccountId is mandatory")
    private Long accountId;

    @NotBlank(message = "Value is mandatory")
    private Double value;

    private String description;

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public OperationRequest(@JsonProperty("accountId") Long accountId, @JsonProperty("value") Double value,
            @JsonProperty("description") String description) {
        this.accountId = accountId;
        this.value = value;
        this.description = description;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
}
