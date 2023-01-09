package cz.hronza.rhrpoc.persistence.repository;


import cz.hronza.rhrpoc.poc_persistence_entity.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {


}
