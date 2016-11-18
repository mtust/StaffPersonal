package com.staff.personal.service.impl;

import com.staff.personal.domain.PremiumFine;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.PremiumFineDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
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
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    @Transactional
    public RestMessageDTO addPremiumFine(Long id, PremiumFineDTO premiumFineDTO) {
        Staff staff = staffRepository.findOne(id);
        PremiumFine premiumFine = new PremiumFine();
        List<PremiumFine> list = staff.getPremiumFines();
        try {
            premiumFine.setName(premiumFineDTO.getName());
            premiumFine.setOrder(premiumFineDTO.getOrder());
            premiumFine.setOrderDate(simpleDateFormat.parse(premiumFineDTO.getOrderDate()));
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
        return staffRepository.findOne(id).getPremiumFines();
    }
}
