package cz.hronza.rhrpoc.persistence.repository;

import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockItemEntity;
import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItemEntity, StockItemId> {

}
