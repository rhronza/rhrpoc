package cz.hronza.rhrpoc.poc_persistence_domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "STOCK", schema = "public")
public class Stock implements Serializable {

    private static final long serialVersionUID = 2117236425856262477L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "area")
    private Integer area;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;
        return getId().equals(stock.getId()) && Objects.equals(getTitle(), stock.getTitle()) && Objects.equals(getArea(), stock.getArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArea());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Stock.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("area=" + area)
                .toString();
    }
}
