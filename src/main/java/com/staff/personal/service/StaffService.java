package com.staff.personal.service;

import java.util.List;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.CreateMainStaffDTO;
import com.staff.personal.dto.RestMessageDTO;

public interface StaffService {

	List<Staff> getAllStaff();
	
	RestMessageDTO deleteData(Long dataId);
	
	RestMessageDTO createMainStaff(CreateMainStaffDTO createMainStaffDTO);
}
