package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.business_logic.domain.MovementRec;
import cz.hronza.rhrpoc.business_logic.domain.Stock;
import cz.hronza.rhrpoc.business_logic.domain.StockIds;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsMovementsRec;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsRec;
import cz.hronza.rhrpoc.core.common.exception.KeyValue;
import cz.hronza.rhrpoc.core.common.exception.RhrPocNotFoundException;
import cz.hronza.rhrpoc.core.common.exception.RhrPocNotSavedException;
import cz.hronza.rhrpoc.persistence.repository.StockItemRepository;
import cz.hronza.rhrpoc.persistence.repository.StockRepository;
import cz.hronza.rhrpoc.persistence.repository.StoredItemRepository;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockEntity;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockItemEntity;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockItemId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockItemRepository stockItemRepository;

    private final StoredItemRepository storedItemRepository;

    public StockServiceImpl(StockRepository stockRepository,
                            StockItemRepository stockItemRepository,
                            StoredItemRepository storedItemRepository) {
        this.stockRepository = stockRepository;
        this.stockItemRepository = stockItemRepository;
        this.storedItemRepository = storedItemRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    // prevents dirty reads, uncommitted changes in concurrent transactions have no impact
    // https://www.baeldung.com/spring-transactional-propagation-isolation
    // Spring @Transactional - isolation, propagation: https://stackoverflow.com/a/8490932
    public StockItemsMovementsRec getStockItems(StockIds stockIdsSearched) {
        StockEntity stockEntity = stockRepository
                .findById(stockIdsSearched.id())
                .orElseThrow(() -> new RhrPocNotFoundException(("stockId not found"), new KeyValue("stockId", stockIdsSearched.id().toString())));

        return new StockItemsMovementsRec(
                stockEntity.getId(),
                stockEntity.getTitle(),
                stockEntity.getArea(),
                stockEntity.getStockItems()
                        .stream()
                        .map(stockItemEntity -> new StockItemsRec(
                                stockItemEntity.getStoredItem().getTitle(),
                                stockItemEntity.getStoredItem().getDescription(),
                                stockEntity.getId(),
                                stockItemEntity.getStoredItem().getId(),
                                stockItemEntity.getCurrentAmount(),
                                stockItemEntity.getMinimalAmount(),
                                stockItemEntity.getDateLastStocking(),
                                stockItemEntity.getDateTimeLastIssue(),
                                stockItemEntity.getWarehouseMovements()
                                        .stream()
                                        .map(movementEntity -> new MovementRec(
                                                movementEntity.getStockItemId().getStockId(),
                                                movementEntity.getStockItemId().getStoredItemId(),
                                                movementEntity.getAmonut(),
                                                movementEntity.getCreated()
                                        )).toList()
                        )).toList()
        );
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE) // has impact to perfomance
    public Long addNewStock(Stock stock) {
        StockEntity saved;
        try {
            saved = stockRepository.save(new StockEntity(
                    null,
                    stock.title(),
                    stock.area(),
                    null
            ));
            stock.itemIds().stream().forEach(storedItemId -> {
                storedItemRepository
                        .findById(storedItemId)
                        .orElseThrow(() -> new RhrPocNotFoundException(("not found"),
                                new KeyValue("itemId", storedItemId.toString()))
                        );

                StockItemEntity stockItemEntity = new StockItemEntity(
                        new StockItemId(saved.getId(), storedItemId),
                        0L,
                        100L,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                stockItemRepository.save(stockItemEntity);
            });
            // catch except RhrPocNotFoundException (https://stackoverflow.com/a/20355868/6289936) :
        } catch (RhrPocNotFoundException ex) {
            throw new RhrPocNotFoundException(ex.getMessage(), ex.getParameters().toArray(new KeyValue[0]));
        } catch (Exception ex) {
            throw new RhrPocNotSavedException("stock not saved",
                    new KeyValue("cause", ex.getCause().toString()),
                    new KeyValue("title", stock.title()),
                    new KeyValue("area", stock.area().toString()),
                    new KeyValue("storedItems", stock.itemIds().toString())
            );
        }
        return saved.getId();
    }
}
