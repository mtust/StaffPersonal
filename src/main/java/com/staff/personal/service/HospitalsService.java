package com.staff.personal.service;

import com.staff.personal.domain.Hospitals;
import com.staff.personal.dto.GroupedHolidayDTO;
import com.staff.personal.dto.GroupedHospitalsDTO;
import com.staff.personal.dto.HospitalsDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by nazar on 17.11.16.
 */
public interface HospitalsService {

    RestMessageDTO addHospitals(Long id,HospitalsDTO hospitalsDTo);

    List<Hospitals> getHospitals(Long id);

    RestMessageDTO delHospitals(Long idStaff, Long idHosp);

    List<HospitalsDTO> createHospitalsDTO(List<Hospitals> list);

    List<Hospitals> updateHospitals(List<HospitalsDTO> hospitals);

    Map<Long, GroupedHospitalsDTO> getGroupedHospitalsByStaffId(Long id);
}
