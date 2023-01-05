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

    @EmbeddedId
    private StockItemId stockItemId;

    @Column(name = "current_amount")
    private Long currentAmount;

    @Column(name = "minimal_amount")
    private Long minimalAmount;

    @Column(name = "date_time_last_issue")
    private OffsetDateTime dateTimeLastIssue;

    @Column(name = "date_last_stocking")
    private Date dateLastStocking;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stockId", insertable=false, updatable=false)
    private StockEntity stockEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "storedItemId", insertable=false, updatable=false)
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

    public Date getDateLastStocking() {
        return dateLastStocking;
    }

    public void setDateLastStocking(Date dateLastStocking) {
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
        if (!(o instanceof StockItemEntity)) return false;
        StockItemEntity stockItemEntity = (StockItemEntity) o;
        return getStockItemId().equals(stockItemEntity.getStockItemId()) && getCurrentAmount().equals(stockItemEntity.getCurrentAmount()) && Objects.equals(getMinimalAmount(), stockItemEntity.getMinimalAmount()) && Objects.equals(getDateTimeLastIssue(), stockItemEntity.getDateTimeLastIssue()) && Objects.equals(getDateLastStocking(), stockItemEntity.getDateLastStocking()) && Objects.equals(getStock(), stockItemEntity.getStock()) && Objects.equals(getStoredItem(), stockItemEntity.getStoredItem()) && Objects.equals(getWarehouseMovements(), stockItemEntity.getWarehouseMovements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockItemId(), getCurrentAmount(), getMinimalAmount(), getDateTimeLastIssue(), getDateLastStocking(), getStock(), getStoredItem(), getWarehouseMovements());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StockItemEntity.class.getSimpleName() + "[", "]")
                .add("stockItemId=" + stockItemId)
                .add("currentAmount=" + currentAmount)
                .add("minimalAmount=" + minimalAmount)
                .add("dateTimeLastIssue=" + dateTimeLastIssue)
                .add("dateLastStocking=" + dateLastStocking)
                .add("stock=" + stockEntity)
                .add("storedItem=" + storedItemEntity)
                .add("warehouseMovements=" + warehouseMovementEntities)
                .toString();
    }
}

