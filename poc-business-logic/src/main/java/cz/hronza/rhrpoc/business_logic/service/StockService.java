package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.business_logic.domain.Stock;
import cz.hronza.rhrpoc.business_logic.domain.StockIds;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsMovementsRec;

public interface StockService {
    StockItemsMovementsRec getStockItems(StockIds stockIds);

    Long addNewStock(Stock stock);
}
