package com.staff.personal.service.nominallyJobBook.impl;

import com.staff.personal.domain.Staff;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.GetStaffDTO;
import com.staff.personal.dto.nominallyJobBook.Statistic;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookParentRepository;
import com.staff.personal.repository.NominallyJobBook.NominallyJobBookRepository;
import com.staff.personal.repository.PositionRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.nominallyJobBook.NominallyJobBookService;
import com.staff.personal.service.nominallyJobBook.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class StatisticServiceImpl implements StatisticService {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    NominallyJobBookRepository nominallyJobBookRepository;

    @Autowired
    NominallyJobBookParentRepository nominallyJobBookParentRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    NominallyJobBookService nominallyJobBookService;

    @Override
    public Statistic getStatisticForParent(Long parentId) {
        Statistic parentStatistic = new Statistic();
        List<Statistic> childStatistics = new ArrayList<>();
        NominallyJobBookParent nominallyJobBookParent = nominallyJobBookParentRepository.findOne(parentId);
        List<NominallyJobBook> nominallyJobBooks = nominallyJobBookParent.getNominallyJobBooks();
        for (NominallyJobBook nominallyJobBook:
             nominallyJobBooks) {
            Statistic statistic = new Statistic();
            List<Position> positions = nominallyJobBook.getPositions();
            List<GetStaffDTO> staffList = nominallyJobBookService.getStaffByNominallyJobBook(nominallyJobBook.getId());
            statistic.setCountOfPosition(positions.size());
            statistic.setCountOfInvolvedPosition(staffList.size());
            statistic.setCountOfNonInvolvedPosition(positions.size() - staffList.size());
            statistic.setPercentageOfNonInvolved(statistic.getCountOfInvolvedPosition().doubleValue() /  statistic.getCountOfPosition() * 100);
            childStatistics.add(statistic);
        }
        parentStatistic.setCountOfPosition(childStatistics.stream().collect(Collectors.summingInt(statistic -> statistic.getCountOfPosition())));
        parentStatistic.setCountOfInvolvedPosition(childStatistics.stream().collect(Collectors.summingInt(statistic -> statistic.getCountOfInvolvedPosition())));
        parentStatistic.setCountOfNonInvolvedPosition(parentStatistic.getCountOfPosition() - parentStatistic.getCountOfInvolvedPosition());
        parentStatistic.setPercentageOfNonInvolved(parentStatistic.getCountOfInvolvedPosition().doubleValue() /  parentStatistic.getCountOfPosition() * 100);
        return  parentStatistic;
    }
}
