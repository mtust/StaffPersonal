package com.staff.personal.service;

import java.util.List;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;

public interface StaffService {

	List<MainStaff> getAllMainStaff();
	
	RestMessageDTO deleteMainStaffById(Long dataId);
	
	RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO);
}
