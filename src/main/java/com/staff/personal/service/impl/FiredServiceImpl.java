package com.staff.personal.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.staff.personal.config.HibernateProxyTypeAdapter;
import com.staff.personal.domain.Fired;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.FiredDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.FiredRepository;
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
    @Autowired
    FiredRepository firedRepository;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");

    @Transactional
    @Override
    public RestMessageDTO addFired(Long id, FiredDTO firedDTO) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }        Fired fired = new Fired();
        try {
            try {
                fired.setDateFiring(simpleDateFormat.parse(firedDTO.getDateFiring()));
                fired.setReferenceLEKDate(simpleDateFormat.parse(firedDTO.getReferenceLEKDate()));
            } catch (ParseException e){
                fired.setDateFiring(simpleDateFormatNew.parse(firedDTO.getDateFiring()));
                fired.setReferenceLEKDate(simpleDateFormatNew.parse(firedDTO.getReferenceLEKDate()));
            }
            fired.setOrderNumber(firedDTO.getOrderNumber());
            fired.setWhereFired(firedDTO.getWhereFired());
            fired.setArticle(firedDTO.getArticle());
            fired.setLastPosition(firedDTO.getLastPosition());
            fired.setSpecialRank(firedDTO.getSpecialRank());
            fired.setMilitaryAccount(firedDTO.getMilitaryAccount());
            fired.setReferenceLEKCertificate(firedDTO.getReferenceLEKCertificate());

            fired.setReferenceLEKNumber(firedDTO.getReferenceLEKNumber());
            fired.setConclusion(firedDTO.getConclusion());
            fired.setSeniority(firedDTO.getSeniority());
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
    public JsonObject getFired(Long id) {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.create();
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return gson.toJsonTree(staff.getFired()).getAsJsonObject();
    }

    @Transactional
    @Override
    public RestMessageDTO delFired(Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        Fired fired = staff.getFired();
        log.info("delete fired is service + " + fired.toString());
        firedRepository.delete(fired);
        staffRepository.save(staff);


        return new RestMessageDTO("Succes", true);
    }
}
