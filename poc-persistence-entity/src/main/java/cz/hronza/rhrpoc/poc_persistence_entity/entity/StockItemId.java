package cz.hronza.rhrpoc.poc_persistence_entity.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class StockItemId implements Serializable {
    private static final long serialVersionUID = 2002462088259277239L;

    private Long stockId;
    private Long storedItemId;

    public StockItemId() {
    }

    public StockItemId(Long stockId, Long storedItemId) {
        this.stockId = stockId;
        this.storedItemId = storedItemId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getStoredItemId() {
        return storedItemId;
    }

    public void setStoredItemId(Long storedItemId) {
        this.storedItemId = storedItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockItemId)) return false;
        StockItemId that = (StockItemId) o;
        return getStockId().equals(that.getStockId()) && getStoredItemId().equals(that.getStoredItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockId(), getStoredItemId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StockItemId.class.getSimpleName() + "[", "]")
                .add("stockId=" + stockId)
                .add("storedItemId=" + storedItemId)
                .toString();
    }
}
