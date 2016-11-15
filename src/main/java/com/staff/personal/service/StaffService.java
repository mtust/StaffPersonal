package com.staff.personal.service;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.GetStaffDTO;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.StaffDTO;

public interface StaffService {

	MainStaff getMainStaffForStuff(Long id);
	
	RestMessageDTO deleteMainStaffById(Long dataId);
	
	RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id);

	RestMessageDTO createStaff(StaffDTO staffDTO);

	RestMessageDTO deleteStaff(Long id);

	GetStaffDTO getStaff(Long id);
}
