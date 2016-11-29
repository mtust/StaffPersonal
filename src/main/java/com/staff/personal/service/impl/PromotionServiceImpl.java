package com.staff.personal.service.impl;

import com.staff.personal.domain.Promotion;
import com.staff.personal.domain.Staff;
import com.staff.personal.dto.PromotionDTO;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.exception.BadRequestParametersException;
import com.staff.personal.exception.ObjectDoNotExistException;
import com.staff.personal.repository.StaffRepository;
import com.staff.personal.service.PromotionService;
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
public class PromotionServiceImpl implements PromotionService{

    @Autowired
    private StaffRepository staffRepository;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateFormatNew = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");


    @Override
    @Transactional
    public RestMessageDTO addPromotion(Long id, PromotionDTO promotionDTO) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        Promotion promotion = new Promotion();
        List<Promotion> list = staff.getPromotions();
        try {
            promotion.setCompanyName(promotionDTO.getCompanyName());
            try{
             promotion.setFromDate(simpleDateFormat.parse(promotionDTO.getFromDate()));
                promotion.setToDate(simpleDateFormat.parse(promotionDTO.getToDate()));
            } catch (ParseException e){
                promotion.setFromDate(simpleDateFormatNew.parse(promotionDTO.getFromDate()));
                promotion.setToDate(simpleDateFormatNew.parse(promotionDTO.getToDate()));
            }
            log.info("addPromotion \n" + promotion.toString());
            list.add(promotion);
            staff.setPromotions(list);
            staffRepository.save(staff);
        }catch (ParseException e) {
            log.warn(e.getMessage());
            throw new BadRequestParametersException("Дата у не вірному форматі");
        }
        return new RestMessageDTO("Succes", true);
    }

    @Override
    @Transactional
    public List<Promotion> getPromotions(Long id) {
        Staff staff = staffRepository.findOne(id);
        if(staff == null){
            throw new ObjectDoNotExistException("staff object with id = " + id + " dosen't exist");
        }
        return staff.getPromotions();
    }
}
