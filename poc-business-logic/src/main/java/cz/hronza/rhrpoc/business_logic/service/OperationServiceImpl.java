package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.core.common.exception.RhrCannotBeDividedByZero;
import cz.hronza.rhrpoc.persistence.repository.StockRepository;
import cz.hronza.rhrpoc.persistence.repository.StoredItemRepository;
import cz.hronza.rhrpoc.poc_persistence_domain.entity.Stock;
import cz.hronza.rhrpoc.poc_persistence_domain.entity.StoredItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    public static final String CANNOT_BE_DIVIDED_BY_0_MESSAGE = "Cannot be divided by 0.";

    private final StockRepository stockRepository;
    private final StoredItemRepository storedItemRepository;

    private final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);

    public OperationServiceImpl(StockRepository stockRepository,
                                StoredItemRepository storedItemRepository) {
        this.stockRepository = stockRepository;
        this.storedItemRepository = storedItemRepository;
    }


    @Override
    public int sum(Integer a, Integer b) {
        List<Stock> all = stockRepository.findAll();
        all.forEach(e -> log.info(e.toString()));
        List<StoredItem> all1 = storedItemRepository.findAll();
        all1.forEach(e -> log.info(e.toString()));
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
            throw new RhrCannotBeDividedByZero(CANNOT_BE_DIVIDED_BY_0_MESSAGE, "message", "variableB is equals 0");
    }

    @Override
    public int multiplication(Integer a, Integer b) {
        return a * b;
    }
}
