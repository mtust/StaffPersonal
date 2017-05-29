package com.staff.personal.controller.api.nominallJobBook;

import com.staff.personal.dto.nominallyJobBook.Statistic;
import com.staff.personal.service.nominallyJobBook.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/nominalJobBook/statistic")
@CrossOrigin
public class StatisticResources {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value = "{parentId}", method = RequestMethod.GET)
    public Statistic getStatistic(@PathVariable("parentId") Long parentId){

    }

}
