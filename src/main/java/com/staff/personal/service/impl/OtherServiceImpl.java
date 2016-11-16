package com.staff.personal.service.impl;

import com.staff.personal.domain.Other;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.OtherRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nazar on 14.11.16.
 */
@Slf4j
@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    OtherRepository otherRepository;

    @Override
    @Transactional
    public RestMessageDTO createOther(Other other, Long id) {
        Staff staff = staffRepository.findOne(id);
        staff.setOther(other);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public Other getOther(Long id) {return staffRepository.getOne(id).getOther();
    }

    @Override
    @Transactional
    public RestMessageDTO delOther(Long id) {
        Staff staff = staffRepository.getOne(id);
        staff.setOther(null);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }
}