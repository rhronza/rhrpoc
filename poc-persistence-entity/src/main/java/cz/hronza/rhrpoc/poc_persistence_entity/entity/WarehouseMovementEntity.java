package cz.hronza.rhrpoc.poc_persistence_entity.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "warehouse_movement")
public class WarehouseMovementEntity implements Serializable {
    private static long serialVersionUID = -5099785722554665660L;

    @Id
    @Column(name = "id", columnDefinition = "bigint")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    StockItemId stockItemId;

    @Column(name = "amount", columnDefinition = "real")
    private Float amonut;

    @Column(name = "created")
    private OffsetDateTime created;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmonut() {
        return amonut;
    }

    public void setAmonut(Float amonut) {
        this.amonut = amonut;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public StockItemId getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(StockItemId stockItemId) {
        this.stockItemId = stockItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseMovementEntity)) return false;
        WarehouseMovementEntity that = (WarehouseMovementEntity) o;
        return getId().equals(that.getId()) && Objects.equals(getStockItemId(), that.getStockItemId()) && Objects.equals(getAmonut(), that.getAmonut()) && Objects.equals(getCreated(), that.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStockItemId(), getAmonut(), getCreated());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WarehouseMovementEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("stockItemId=" + stockItemId)
                .add("amonut=" + amonut)
                .add("created=" + created)
                .toString();
    }
}
