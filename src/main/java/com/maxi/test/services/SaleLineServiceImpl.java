package com.maxi.test.services;

import com.maxi.test.dao.check.Check;
import com.maxi.test.dao.salelines.SaleLine;
import com.maxi.test.dao.salelines.SaleLineService;
import com.maxi.test.dao.salelines.SaleLinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleLineServiceImpl implements SaleLineService {

    private final SaleLinesRepository saleLinesRepository;

    @Autowired
    public SaleLineServiceImpl(SaleLinesRepository saleLinesRepository) {
        this.saleLinesRepository = saleLinesRepository;
    }

    @Override
    public SaleLine save(SaleLine s) {
        return saleLinesRepository.save(s);
    }

    @Override
    @Transactional
    public void saveAll(Iterable<SaleLine> lines) {
        saleLinesRepository.saveAll(lines);
    }

    @Override
    @Transactional
    public SaleLine saveAndFlush(SaleLine s) {
        return saleLinesRepository.saveAndFlush(s);
    }

    @Override
    public List<Object> getTopThreeThings(Integer kpp) {
        return saleLinesRepository.getTopThreeThings(kpp, PageRequest.of(0, 3));
    }
}
