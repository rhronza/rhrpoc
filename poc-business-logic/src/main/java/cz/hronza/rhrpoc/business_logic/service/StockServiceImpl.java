package cz.hronza.rhrpoc.business_logic.service;

import cz.hronza.rhrpoc.business_logic.domain.MovementRec;
import cz.hronza.rhrpoc.business_logic.domain.Stock;
import cz.hronza.rhrpoc.business_logic.domain.StockIds;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsMovementsRec;
import cz.hronza.rhrpoc.business_logic.domain.StockItemsRec;
import cz.hronza.rhrpoc.core.common.exception.KeyValue;
import cz.hronza.rhrpoc.core.common.exception.RhrPocNotFoundException;
import cz.hronza.rhrpoc.persistence.repository.StockItemRepository;
import cz.hronza.rhrpoc.persistence.repository.StockRepository;
import cz.hronza.rhrpoc.persistence.repository.StoredItemRepository;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockEntity;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockItemEntity;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockItemId;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StoredItemEntity;
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
    @Transactional(isolation = Isolation.SERIALIZABLE) // has impact to performance
    public Long addNewStock(Stock stock) {
        StockEntity saved;
        saved = stockRepository.save(new StockEntity(
                null,
                stock.title(),
                stock.area(),
                null
        ));
        stock.itemIds().forEach(storedItemId -> {
            StoredItemEntity itemId = storedItemRepository
                    .findById(storedItemId)
                    .orElseThrow(() -> new RhrPocNotFoundException(("not found"),
                            new KeyValue("itemId", storedItemId.toString()))
                    );
            stockItemRepository.save(new StockItemEntity(
                    new StockItemId(saved.getId(), itemId.getId()),
                    0L,
                    100L,
                    null,
                    null,
                    null,
                    null,
                    null
            ));
        });
        return saved.getId();
    }
}
