package com.staff.personal.service;

import com.staff.personal.domain.MainStaff;
import com.staff.personal.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StaffService {

	MainStaff getMainStaffForStuff(Long id);
	
	RestMessageDTO deleteMainStaffById(Long dataId);
	
	RestMessageDTO createMainStaff(MainStaffDTO mainStaffDTO, Long id);

	RestMessageDTO createStaff(StaffDTO staffDTO);

	RestMessageDTO deleteStaff(Long id);

	GetStaffDTO getStaff(Long id);

	List<GetAllStaffDTO> getWholeStaff();

	List<GetStaffDTO> getAllStaff();

	RestMessageDTO updateStaffById(Long id, StaffDTO staffDTO);

	RestMessageDTO updateAllStaffById(Long id, AllStaffDTO allStaffDTO);

<<<<<<< HEAD
	GetAllStaffDTO getWholeStaff(Long id);
=======
	GetAllStaffDTO getAllStaff(Long id);

	@Transactional
	RestMessageDTO updateWholeStuffFieldById(Long id, AllStaffDTO staffDTO);
>>>>>>> c2aa9b404b3a4dfa0c8e16f12065b87d93115836
}
