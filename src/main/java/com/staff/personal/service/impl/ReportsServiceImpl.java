package com.staff.personal.service.impl;

import com.staff.personal.domain.Reports;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.StuffDocuments;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.ReportsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
@Slf4j
@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    @Transactional
    public RestMessageDTO addReport(Long id, MultipartFile multipartFile, String name, String text) throws IOException{
        Staff staff = staffRepository.findOne(id);
        Reports reports = new Reports();
        List<Reports> list = staff.getReports();
        reports.setFile(multipartFile.getBytes());
        reports.setName(name);
        reports.setText(text);
        list.add(reports);
        staff.setReports(list);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }

    @Override
    public List<Reports> getReports(Long id) {
        return null;
    }
}
