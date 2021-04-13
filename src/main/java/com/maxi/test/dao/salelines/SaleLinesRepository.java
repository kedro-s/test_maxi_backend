package com.maxi.test.dao.salelines;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SaleLinesRepository extends JpaRepository<SaleLine, Long> {
    @Query( "select s.articleNumber, s.name, count(s) as cnt " +
            "from SaleLine s join s.check c " +
            "where c.kppNumber = :kpp_num " +
            "group by s.name, s.articleNumber " +
            "order by cnt desc ")
    List<Object> getTopThreeThings(@Param("kpp_num") Integer kpp_num, Pageable pageable);
}
