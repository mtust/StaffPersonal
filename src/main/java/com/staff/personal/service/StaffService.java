package com.staff.personal.service;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.*;

import java.util.List;

public interface StaffService {

	MainStaff getMainStaffForStuff(Long id);
	
	RestMessageDTO deleteMainStaffById(Long dataId);
	
	RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id);

	RestMessageDTO createStaff(StaffDTO staffDTO);

	RestMessageDTO deleteStaff(Long id);

	GetStaffDTO getStaff(Long id);

	List<GetAllStaffDTO> getStaff();

	List<GetStaffDTO> getAllStaff();

	RestMessageDTO updateStaffById(Long id, StaffDTO staffDTO);

	RestMessageDTO updateAllStaffById(Long id, AllStaffDTO allStaffDTO);

	GetAllStaffDTO getAllStaff(Long id);
}
