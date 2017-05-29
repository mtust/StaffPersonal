package com.staff.personal.dto.nominallyJobBook;

import lombok.Data;

@Data
public class Statistic {

    private Integer countOfPosition;
    private Integer countOfInvolvedPosition;
    private Integer countOfNonInvolvedPosition;
    private Double percentageOfNonInvolved;

}
