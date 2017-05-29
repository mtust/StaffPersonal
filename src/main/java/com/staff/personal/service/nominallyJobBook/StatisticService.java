package com.staff.personal.service.nominallyJobBook;

import com.staff.personal.dto.nominallyJobBook.Statistic;
import org.springframework.stereotype.Service;

@Service
public interface StatisticService {

    Statistic getStatisticForParent(Long parentId);

}
