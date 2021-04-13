package com.maxi.test.dao.salelines;

import com.maxi.test.dao.check.Check;

import javax.persistence.*;

@Entity
@Table(name = "SaleLines",
        indexes = {
            @Index(columnList = "check_id", name = "i_check")
        })
public class SaleLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private Long lineId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "check_id", foreignKey = @ForeignKey(name = "fk_check"))
    private Check check;

    @Column(name = "article", nullable = false)
    private Integer articleNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price_one", nullable = false, precision = 2)
    private Double priceOne;

    public SaleLine() {
    }

    public SaleLine(Long lineId, Integer articleNumber, Integer quantity, Double priceOne) {
        this.lineId = lineId;
        this.articleNumber = articleNumber;
        this.quantity = quantity;
        this.priceOne = priceOne;
    }

    public SaleLine(Integer articleNumber, String name, Integer quantity, Double priceOne) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.quantity = quantity;
        this.priceOne = priceOne;
    }

    public Long getLineId() {
        return lineId;
    }

    public Integer getArticleNumber() {
        return articleNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPriceOne() {
        return priceOne;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public void setArticleNumber(Integer articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPriceOne(Double priceOne) {
        this.priceOne = priceOne;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
