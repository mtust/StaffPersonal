package com.staff.personal.service;

import com.staff.personal.domain.Promotion;
import com.staff.personal.dto.PromotionDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
public interface PromotionService {

    RestMessageDTO addPromotion(Long id,PromotionDTO promotionDTO);

    List<Promotion> getPromotions(Long id);
}
