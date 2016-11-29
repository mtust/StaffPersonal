package com.staff.personal.service;

import com.staff.personal.domain.Hospitals;
import com.staff.personal.dto.HospitalsDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;

/**
 * Created by nazar on 17.11.16.
 */
public interface HospitalsService {

    RestMessageDTO addHospitals(Long id,HospitalsDTO hospitalsDTo);

    List<Hospitals> getHospitals(Long id);

    RestMessageDTO delHospitals(Long idStaff, Long idHosp);
}
