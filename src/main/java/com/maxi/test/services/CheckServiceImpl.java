package com.maxi.test.services;

import com.maxi.test.dao.check.Check;
import com.maxi.test.dao.check.CheckRepository;
import com.maxi.test.dao.check.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class CheckServiceImpl implements CheckService {
    private final CheckRepository checkRepository;

    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @Override
    @Transactional
    public Check save(Check c) {
        return checkRepository.save(c);
    }


    @Transactional
    public Double getTotalSumByDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Double result = checkRepository.getTotalSumByDay(
                date,
                calendar.getTime()
        );
        if (result == null) {
            return 0.0;
        }
        return result;
    }

    @Override
    @Transactional
    public void saveAll(List<Check> checks) {
        checkRepository.flush();
        checkRepository.saveAll(checks);
    }
}
