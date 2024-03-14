package killian.brunet.sg.kata.models.dto.response;

import killian.brunet.sg.kata.models.enums.OperationType;

public class OperationResponse {

    private Double value;
    private String date;
    private String operationType;

    public OperationResponse(Double value, String date, OperationType operationType) {
        this.value = value;
        this.date = date;
        this.operationType = operationType.toString();
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType.toString();
    }
}
