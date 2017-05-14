package com.staff.personal.dto.nominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.NominallyJobBookParent;
import lombok.Data;

import java.util.List;

@Data
public class NominallyJobBookParentStaffDTO {

    private List<NominallyJobBookStaffDTO> nominallyJobBookStaffDTO;
    private NominallyJobBookParent nominallyJobBookParent;

}
