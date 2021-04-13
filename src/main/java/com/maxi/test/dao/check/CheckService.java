package com.maxi.test.dao.check;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface CheckService {
    Check save(Check c);
    Double getTotalSumByDay(Date date);
    void saveAll(List<Check> checks);
}
