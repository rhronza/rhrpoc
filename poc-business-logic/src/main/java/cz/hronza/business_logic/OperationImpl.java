package cz.hronza.business_logic;

import org.springframework.stereotype.Service;

@Service
public class OperationImpl implements Operation {
    @Override
    public int sum(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public int difference(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public int divide(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public int multiplication(Integer a, Integer b) {
        return a * b;
    }
}
