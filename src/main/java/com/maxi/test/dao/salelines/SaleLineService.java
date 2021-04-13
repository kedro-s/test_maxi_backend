package com.maxi.test.dao.salelines;

import com.maxi.test.dao.check.Check;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SaleLineService {
    SaleLine save(SaleLine s);
    void saveAll(Iterable<SaleLine> lines);
    SaleLine saveAndFlush(SaleLine s);
    List<Object> getTopThreeThings(Integer kpp);
}
