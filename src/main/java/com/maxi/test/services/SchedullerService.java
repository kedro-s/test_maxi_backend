package com.maxi.test.services;

import com.maxi.test.dao.check.Check;
import com.maxi.test.dao.check.CheckService;
import com.maxi.test.dao.salelines.SaleLine;
import com.maxi.test.dao.salelines.SaleLineService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@EnableScheduling
@Service
public class SchedullerService {

    private ApplicationContext context;

    @Autowired
    public SchedullerService(ApplicationContext context) {
        this.context = context;
    }

    @Scheduled(fixedRate = 10 * 60 * 1000L)
    public void task() {
        XmlToDatabaseService xmlService = (XmlToDatabaseService) context.getBean("xmlService");
        xmlService.execute();
    }
}

