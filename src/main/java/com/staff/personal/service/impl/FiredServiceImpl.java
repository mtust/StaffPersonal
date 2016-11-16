package com.staff.personal.service.impl;

import com.staff.personal.domain.Fired;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.FiredDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.FiredService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by nazar on 16.11.16.
 */
@Slf4j
@Service
public class FiredServiceImpl implements FiredService {

    @Autowired
    StaffRepository staffRepository;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Transactional
    @Override
    public RestMessageDTO addFired(Long id, FiredDTO firedDTO) {
        Staff staff = staffRepository.findOne(id);
        Fired fired = new Fired();
        try {
            fired.setDateFiring(simpleDateFormat.parse(firedDTO.getDateFiring()));
            fired.setOrderNumber(firedDTO.getOrderNumber());
            fired.setWhereFired(firedDTO.getWhereFired());
            fired.setArticle(firedDTO.getArticle());
            fired.setLastPosition(firedDTO.getLastPosition());
            fired.setSpecialRank(firedDTO.getSpecialRank());
            fired.setMilitaryAccount(firedDTO.getMilitaryAccount());
            fired.setReferenceLEKCertificate(firedDTO.getReferenceLEKCertificate());
            fired.setReferenceLEKDate(simpleDateFormat.parse(firedDTO.getReferenceLEKDate()));
            fired.setReferenceLEKNumber(firedDTO.getReferenceLEKNumber());
            fired.setConclusion(firedDTO.getConclusion());
            fired.setPersonalFileForwarded(firedDTO.getPersonalFileForwarded());
            staff.setFired(fired);
            staffRepository.save(staff);
            log.info("fired after save \n" + fired.toString());
        } catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public Fired getFired(Long id) {
        log.info("getFired \n" + staffRepository.findOne(id).getFired());
        return staffRepository.findOne(id).getFired();
    }

    @Transactional
    @Override
    public RestMessageDTO delFired(Long id) {
        return null;
    }
}
