package com.staff.personal.service.impl;

import com.staff.personal.domain.PremiumFine;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.PremiumFineDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.PremiumFineRepository;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.PremiumFineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
@Slf4j
@Service
public class PremiumFineServiceImpl implements PremiumFineService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PremiumFineRepository premiumFineRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");

    @Override
    @Transactional
    public RestMessageDTO addPremiumFine(Long id, PremiumFineDTO premiumFineDTO) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        PremiumFine premiumFine = new PremiumFine();
        List<PremiumFine> list = staff.getPremiumFines();
        try {
            premiumFine.setName(premiumFineDTO.getName());
            premiumFine.setOrder(premiumFineDTO.getOrder());
            try {
                premiumFine.setOrderDate(simpleDateFormat.parse(premiumFineDTO.getOrderDate()));
            } catch (ParseException e){
                premiumFine.setOrderDate(simpleDateFormatNew.parse(premiumFineDTO.getOrderDate()));
            }
            premiumFine.setSerialNumber(premiumFineDTO.getSerialNumber());
            log.info("add premiumFine \n" + premiumFine.toString());
            list.add(premiumFine);
            staff.setPremiumFines(list);
            staffRepository.save(staff);
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<PremiumFine> getPremiumFine(Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return staff.getPremiumFines();
    }

    @Override
    @Transactional
    public RestMessageDTO delPremiumFine(Long idSt, Long idPrFine) {
        Staff staff = staffRepository.findOne(idSt);
        if(staff == null || staff.getIsDeleted() == true){
            throw new ObjectDoNotExistException("staff object with id = " + idSt + " dosen't exist or is deleted");
        }
        List<PremiumFine> list = staff.getPremiumFines();
        for (PremiumFine premiumFine : list) {
            if (premiumFine.getId() == idPrFine){
                list.remove(premiumFine);
                premiumFineRepository.delete(premiumFine);
                break;
            }
        }
        staff.setPremiumFines(list);
        staffRepository.save(staff);
        return new RestMessageDTO("Succes", true);
    }
}
