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

	GetAllStaffDTO getWholeStaff(Long id);

	@Transactional
	RestMessageDTO updateWholeStaffByIdPatch(Long id, AllStaffDTO allStaffDTO);

	List<GetStaffDTO> getAllDeletedStaff();

	RestMessageDTO deleteByOperator(Long id);

	List<GetStaffDTO> getAllStaffDeletedByOperator();

	RestMessageDTO restoreDeletedStaffByAdmin(Long id);

	RestMessageDTO restoreDeletedStaffByOperator(Long id);

    List<GetStaffDTO> getStaffByPositionCode(String code);

}
