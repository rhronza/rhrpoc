package cz.hronza.rhrpoc.poc_persistence_entity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "stock_item")
public class StockItem implements Serializable {

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
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "storedItemId", insertable=false, updatable=false)
    private StoredItem storedItem;

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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public StoredItem getStoredItem() {
        return storedItem;
    }

    public void setStoredItem(StoredItem storedItem) {
        this.storedItem = storedItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockItem)) return false;
        StockItem stockItem = (StockItem) o;
        return getStockItemId().equals(stockItem.getStockItemId()) && Objects.equals(getCurrentAmount(), stockItem.getCurrentAmount()) && Objects.equals(getMinimalAmount(), stockItem.getMinimalAmount()) && Objects.equals(getDateTimeLastIssue(), stockItem.getDateTimeLastIssue()) && Objects.equals(getDateLastStocking(), stockItem.getDateLastStocking()) && Objects.equals(getStock(), stockItem.getStock()) && Objects.equals(getStoredItem(), stockItem.getStoredItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockItemId(), getCurrentAmount(), getMinimalAmount(), getDateTimeLastIssue(), getDateLastStocking(), getStock(), getStoredItem());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StockItem.class.getSimpleName() + "[", "]")
                .add("stockItemId=" + stockItemId)
                .add("currentAmount=" + currentAmount)
                .add("minimalAmount=" + minimalAmount)
                .add("dateTimeLastIssue=" + dateTimeLastIssue)
                .add("dateLastStocking=" + dateLastStocking)
                .add("stock=" + stock)
                .add("storedItem=" + storedItem)
                .toString();
    }
}

