package cz.hronza.rhrpoc.business_logic.domain;


import cz.hronza.rhrpoc.core.common.enums.OperationsEnum;

public class Result {

    private Integer varA;
    private Integer varB;
    private OperationsEnum operationsEnum;
    private Integer result;

    public Integer getVarA() {
        return varA;
    }

    public Result setVarA(Integer varA) {
        this.varA = varA;
        return this;
    }

    public Integer getVarB() {
        return varB;
    }

    public Result setVarB(Integer varB) {
        this.varB = varB;
        return this;
    }

    public OperationsEnum getOperationsEnum() {
        return operationsEnum;
    }

    public Result setOperationsEnum(OperationsEnum operationsEnum) {
        this.operationsEnum = operationsEnum;
        return this;
    }

    public Integer getResult() {
        return result;
    }

    public Result setResult(Integer result) {
        this.result = result;
        return this;
    }
}
