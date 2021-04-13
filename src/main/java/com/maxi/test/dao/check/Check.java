package com.maxi.test.dao.check;

import com.maxi.test.dao.salelines.SaleLine;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Checks",
        indexes = {
                @Index(columnList = "printed_date", name = "i_printed_date"),
                @Index(columnList = "kpp_num", name = "i_kpp_number"),
        }
)
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "printed_date", nullable = false)
    private Timestamp printedDate;

    @Column(name = "kpp_num", nullable = false)
    private Integer kppNumber;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "check")
    private final Set<SaleLine> lines = new HashSet<>();

    public Check() {
    }

    public Check(Integer kpp_number, Date printedDate) {
        this.printedDate = (Timestamp) printedDate;
        this.kppNumber = kpp_number;
    }

    public Check(Long id, Date printedDate, Integer kppNumber) {
        this.id = id;
        this.printedDate = (Timestamp) printedDate;
        this.kppNumber = kppNumber;
    }

    public void setPrintedDate(String timestamp) {
        this.printedDate = (Timestamp) new Date(Long.parseLong(timestamp));
    }

    public Long getId() {
        return id;
    }

    public Date getPrintedDate() {
        return printedDate;
    }

    public Integer getKppNumber() {
        return kppNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrintedDate(Timestamp printedDate) {
        this.printedDate = printedDate;
    }

    public void setKppNumber(Integer kpp_number) {
        this.kppNumber = kpp_number;
    }

}
