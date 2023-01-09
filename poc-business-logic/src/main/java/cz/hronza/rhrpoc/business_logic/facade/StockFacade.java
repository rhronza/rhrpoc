package cz.hronza.rhrpoc.business_logic.facade;

import cz.hronza.rhrpoc.business_logic.domain.Stock;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsMovementsRec;
import cz.hronza.rhrpoc.business_logic.domain.StockIds;

import java.util.List;

public interface StockFacade {

    List<StockItemsMovementsRec> getStockItemsAndMovements(List<StockIds> stockIds);

    Long addNewStock(Stock stock);

}
