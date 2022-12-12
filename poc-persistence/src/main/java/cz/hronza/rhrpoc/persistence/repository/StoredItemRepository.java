package cz.hronza.rhrpoc.persistence.repository;

import cz.hronza.rhrpoc.poc_persistence_domain.entity.StoredItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoredItemRepository extends JpaRepository<StoredItem, Long> {
    @Override
    List<StoredItem> findAll();
}
