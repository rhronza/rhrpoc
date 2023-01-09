package cz.hronza.rhrpoc.poc_persistence_entity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "stock_item")
public class StockItemEntity implements Serializable {

    private static final long serialVersionUID = -7994504278623875431L;

    public StockItemEntity() {
    }

    public StockItemEntity(StockItemId stockItemId, Long currentAmount, Long minimalAmount, OffsetDateTime dateTimeLastIssue, LocalDate dateLastStocking,  StockEntity stockEntity, StoredItemEntity storedItemEntity, List<WarehouseMovementEntity> warehouseMovementEntities) {
        this.stockItemId = stockItemId;
        this.currentAmount = currentAmount;
        this.minimalAmount = minimalAmount;
        this.dateTimeLastIssue = dateTimeLastIssue;
        this.dateLastStocking = dateLastStocking;
        this.stockEntity = stockEntity;
        this.storedItemEntity = storedItemEntity;
        this.warehouseMovementEntities = warehouseMovementEntities;
    }

    @EmbeddedId
    private StockItemId stockItemId;

    @Column(name = "current_amount")
    private Long currentAmount;

    @Column(name = "minimal_amount")
    private Long minimalAmount;

    @Column(name = "date_time_last_issue")
    private OffsetDateTime dateTimeLastIssue;

    @Column(name = "date_last_stocking")
    private LocalDate dateLastStocking;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", insertable=false, updatable=false)
    private StockEntity stockEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="stored_item_id", insertable=false, updatable=false)
    private StoredItemEntity storedItemEntity;

    @OneToMany(mappedBy = "stockItemId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WarehouseMovementEntity> warehouseMovementEntities;

    public Long getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Long currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Long getMinimalAmount() {
        return minimalAmount;
    }

    public void setMinimalAmount(Long minimalAmount) {
        this.minimalAmount = minimalAmount;
    }

    public OffsetDateTime getDateTimeLastIssue() {
        return dateTimeLastIssue;
    }

    public void setDateTimeLastIssue(OffsetDateTime dateTimeLastIssue) {
        this.dateTimeLastIssue = dateTimeLastIssue;
    }

    public LocalDate getDateLastStocking() {
        return dateLastStocking;
    }

    public void setDateLastStocking(LocalDate dateLastStocking) {
        this.dateLastStocking = dateLastStocking;
    }

    public StockItemId getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(StockItemId stockItemId) {
        this.stockItemId = stockItemId;
    }

    public StockEntity getStock() {
        return stockEntity;
    }

    public void setStock(StockEntity stockEntity) {
        this.stockEntity = stockEntity;
    }

    public StoredItemEntity getStoredItem() {
        return storedItemEntity;
    }

    public void setStoredItem(StoredItemEntity storedItemEntity) {
        this.storedItemEntity = storedItemEntity;
    }

    public List<WarehouseMovementEntity> getWarehouseMovements() {
        return warehouseMovementEntities;
    }

    public void setWarehouseMovements(List<WarehouseMovementEntity> warehouseMovementEntities) {
        this.warehouseMovementEntities = warehouseMovementEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockItemEntity that)) return false;

        if (getStockItemId() != null ? !getStockItemId().equals(that.getStockItemId()) : that.getStockItemId() != null)
            return false;
        if (getCurrentAmount() != null ? !getCurrentAmount().equals(that.getCurrentAmount()) : that.getCurrentAmount() != null)
            return false;
        if (getMinimalAmount() != null ? !getMinimalAmount().equals(that.getMinimalAmount()) : that.getMinimalAmount() != null)
            return false;
        if (getDateTimeLastIssue() != null ? !getDateTimeLastIssue().equals(that.getDateTimeLastIssue()) : that.getDateTimeLastIssue() != null)
            return false;
        if (getDateLastStocking() != null ? !getDateLastStocking().equals(that.getDateLastStocking()) : that.getDateLastStocking() != null)
            return false;
        if (!Objects.equals(stockEntity, that.stockEntity)) return false;
        if (!Objects.equals(storedItemEntity, that.storedItemEntity))
            return false;
        return Objects.equals(warehouseMovementEntities, that.warehouseMovementEntities);
    }

    @Override
    public int hashCode() {
        int result = getStockItemId() != null ? getStockItemId().hashCode() : 0;
        result = 31 * result + (getCurrentAmount() != null ? getCurrentAmount().hashCode() : 0);
        result = 31 * result + (getMinimalAmount() != null ? getMinimalAmount().hashCode() : 0);
        result = 31 * result + (getDateTimeLastIssue() != null ? getDateTimeLastIssue().hashCode() : 0);
        result = 31 * result + (getDateLastStocking() != null ? getDateLastStocking().hashCode() : 0);
        result = 31 * result + (stockEntity != null ? stockEntity.hashCode() : 0);
        result = 31 * result + (storedItemEntity != null ? storedItemEntity.hashCode() : 0);
        result = 31 * result + (warehouseMovementEntities != null ? warehouseMovementEntities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StockItemEntity.class.getSimpleName() + "[", "]")
                .add("stockItemId=" + stockItemId)
                .add("currentAmount=" + currentAmount)
                .add("minimalAmount=" + minimalAmount)
                .add("dateTimeLastIssue=" + dateTimeLastIssue)
                .add("dateLastStocking=" + dateLastStocking)
                .add("stockEntity=" + stockEntity)
                .add("storedItemEntity=" + storedItemEntity)
                .add("warehouseMovementEntities=" + warehouseMovementEntities)
                .toString();
    }
}

