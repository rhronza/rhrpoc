package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.Stock;
import cz.hronza.rhrpoc.business_logic.domain.StockIds;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsMovementsRec;
import cz.hronza.rhrpoc.business_logic.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockFacadeImpl implements StockFacade {

    private final StockService stockService;

    public StockFacadeImpl(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    public List<StockItemsMovementsRec> getStockItemsAndMovements(List<StockIds> stockIds) {
        return stockIds
                .stream()
                .map(stockService::getStockItems)
                .toList();
    }

    @Override
    public Long addNewStock(Stock stock) {
        return stockService.addNewStock(stock);
    }
}
