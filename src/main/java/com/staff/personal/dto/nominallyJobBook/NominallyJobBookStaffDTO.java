package com.staff.personal.dto.nominallyJobBook;

import com.staff.personal.domain.nominallyJobBooks.NominallyJobBook;
import com.staff.personal.dto.GetStaffDTO;
import lombok.Data;

import java.util.List;

@Data
public class NominallyJobBookStaffDTO {

    List<GetStaffDTO> getStaffDTOS;
    NominallyJobBook nominallyJobBook;


}
