package com.maxi.test.controllers;

import com.maxi.test.dao.check.CheckService;
import com.maxi.test.dao.salelines.SaleLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class SaleController {

    private final CheckService checkService;
    private final SaleLineService saleLineService;

    @Autowired
    public SaleController(CheckService checkService, SaleLineService saleLineService) {
        this.checkService = checkService;
        this.saleLineService = saleLineService;
    }

    @GetMapping(value = "")
    public String index() {
        return "index";
    }

    @GetMapping(value = "totalSum")
    public String totalSum() {
        return "totalSum";
    }

    @PostMapping(value = "totalSum", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postTotalSum(@RequestParam("date") String strDate, Model model) {
        DateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatOut = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;
        try {
            date = formatIn.parse(strDate);
            Double totalSum =  checkService.getTotalSumByDay(date);
            model.addAttribute("totalSum", String.format("%.2f", totalSum));
            model.addAttribute("date", formatOut.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "totalSum";
    }


    @GetMapping(value = "topThingsByKpp")
    public String viewTopThingsByKpp() {
        return "topThingsByKpp";
    }

    @PostMapping(value = "topThingsByKpp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postTopThingByKpp(@RequestParam("kpp") Integer kpp, Model model) {
        List<Object> res = saleLineService.getTopThreeThings(kpp);
        model.addAttribute("positions", res);
        model.addAttribute("kpp", kpp);
        return "topThingsByKpp";
    }

}
