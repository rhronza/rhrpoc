package cz.hronza.restapi.dto;

import cz.hronza.business_logic.enumer.OperationsEnum;

public class ResultDto {
    private Integer variableA;
    private Integer variableB;
    private OperationsEnum operationsEnum;
    private String result;


    public String getResult() {
        return result;
    }

    public ResultDto setResult(String result) {
        this.result = result;
        return this;
    }

    public Integer getVariableA() {
        return variableA;
    }

    public ResultDto setVariableA(Integer variableA) {
        this.variableA = variableA;
        return this;
    }

    public Integer getVariableB() {
        return variableB;
    }

    public ResultDto setVariableB(Integer variableB) {
        this.variableB = variableB;
        return this;
    }

    public OperationsEnum getOperationsEnum() {
        return operationsEnum;
    }

    public ResultDto setOperationsEnum(OperationsEnum operationsEnum) {
        this.operationsEnum = operationsEnum;
        return this;
    }
}
