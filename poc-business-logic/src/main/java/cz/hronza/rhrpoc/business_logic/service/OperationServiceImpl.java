package cz.hronza.rhrpoc.business_logic.service;

import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {
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
        if (b != 0)
            return a / b;
        else
            // TODO vyhoď vlastní výjimku
            return Integer.MIN_VALUE;
    }

    @Override
    public int multiplication(Integer a, Integer b) {
        return a * b;
    }
}
