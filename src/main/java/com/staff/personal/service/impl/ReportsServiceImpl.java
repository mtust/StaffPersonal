package com.staff.personal.service.impl;

import com.staff.personal.domain.Reports;
import com.staff.personal.domain.Staff;
import com.staff.personal.domain.StuffDocuments;
import com.staff.personal.dto.GetReportsInfoDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.ReportsRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.ReportsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
@Slf4j
@Service
public class ReportsServiceImpl implements ReportsService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ReportsRepository reportsRepository;

    @Override
    @Transactional
    public RestMessageDTO addReport(Long id, MultipartFile multipartFile, String name, String text) throws IOException{
        Staff staff = staffRepository.findOne(id);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
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
    @Transactional
    public List<GetReportsInfoDTO> getReportsInfo(Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist or is deleted");
        }
        List<Reports> list = staff.getReports();
        List<GetReportsInfoDTO> reportsInfoDTOs = new ArrayList<>();
        for (Reports reports : list) {
        GetReportsInfoDTO getReportsInfoDTO = new GetReportsInfoDTO();
            getReportsInfoDTO.setName(reports.getName());
            getReportsInfoDTO.setText(reports.getText());
            getReportsInfoDTO.setId(reports.getId().toString());
            reportsInfoDTOs.add(getReportsInfoDTO);
        }
        return reportsInfoDTOs;
    }

    @Override
    @Transactional
    public RestMessageDTO delReport(Long idS, Long idReports) {
        Staff staff = staffRepository.findOne(idS);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + idS + " dosen't exist or is deleted");
        }
        log.info("in delReport");
        Collection<Reports> list = staff.getReports();
        for (Reports reports : list) {
            log.info("in foreach");
            if (reports.getId() == idReports){
                log.info("in if statment");
                list.remove(reports);
                reportsRepository.delete(reports);
                break;
            }
        }
        return new RestMessageDTO("Succes", true);
    }



   /* @Transactional
    @Override
    public byte[] getReportsFile(Long id){
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        List<Reports> list = staff.getReports();
        Reports reports = list.get(2);
        return reports.getFile();
    }*/
}
