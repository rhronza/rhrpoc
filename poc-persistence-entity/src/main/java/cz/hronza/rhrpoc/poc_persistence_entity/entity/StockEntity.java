package cz.hronza.rhrpoc.poc_persistence_entity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "stock", schema = "public")
public class StockEntity implements Serializable {

    private static final long serialVersionUID = 2117236425856262477L;

    public StockEntity() {
    }

    public StockEntity(Long id, String title, Integer area, List<StockItemEntity> stockItemEntities) {
        this.id = id;
        this.title = title;
        this.area = area;
        this.stockItemEntities = stockItemEntities;
    }

    @Id
    @Column(name = "id")
    // on postgres sequence must be defined SEQUENCER, for example: CREATE SEQUENCE serial START 101
    // https://stackoverflow.com/questions/38194364/how-to-get-list-of-sequence-names-in-postgres
    // or type of id column: SERIAL and GenerationType.IDENTITY,
    // https://www.tutorialspoint.com/postgresql/postgresql_using_autoincrement.htm
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "serial")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "area")
    private Integer area;

    @OneToMany(mappedBy = "stockEntity", cascade = CascadeType.ALL /*, orphanRemoval = true*/, fetch = FetchType.LAZY)
    private List<StockItemEntity> stockItemEntities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public List<StockItemEntity> getStockItems() {
        return stockItemEntities;
    }

    public void setStockItems(List<StockItemEntity> stockItemEntities) {
        this.stockItemEntities = stockItemEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockEntity)) return false;
        StockEntity stockEntity = (StockEntity) o;
        return getId().equals(stockEntity.getId()) && Objects.equals(getTitle(), stockEntity.getTitle()) && Objects.equals(getArea(), stockEntity.getArea()) && Objects.equals(getStockItems(), stockEntity.getStockItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArea(), getStockItems());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", StockEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("area=" + area)
                .add("stockItems=" + stockItemEntities)
                .toString();
    }
}
