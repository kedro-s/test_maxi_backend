package com.maxi.test.dao.check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CheckRepository extends JpaRepository<Check, Long> {

    @Query("select sum(s.quantity * s.priceOne) from SaleLine s inner join s.check c where s.check.printedDate between :date_start and :date_end")
    Double getTotalSumByDay(@Param("date_start") Date date_start, @Param("date_end") Date date_end);

    @Override
    <S extends Check> List<S> saveAll(Iterable<S> iterable);

    @Override
    void flush();
}
