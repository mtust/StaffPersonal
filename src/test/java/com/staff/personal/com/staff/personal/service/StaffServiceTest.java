package com.staff.personal.com.staff.personal.service;

import com.staff.personal.dto.AllStaffDTO;
import com.staff.personal.dto.MainStaffDTO;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mtustanovskyy on 11/28/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StaffServiceTest {


    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    @Test
    public void updateWholeStuffFieldByIdTest(){
        AllStaffDTO allStaffDTO = new AllStaffDTO();
        MainStaffDTO mainStaffDTO = new MainStaffDTO();
        mainStaffDTO.setFullName("pezda");
        allStaffDTO.setMainStaff(mainStaffDTO);
        log.info("allStaffDTO" + allStaffDTO);
        staffService.updateWholeStuffFieldById(new Long(1),allStaffDTO);
    }

}
