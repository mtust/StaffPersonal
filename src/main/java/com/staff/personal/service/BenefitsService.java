package com.staff.personal.service;

import com.staff.personal.domain.Benefits;
import com.staff.personal.dto.BenefitsDTO;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nazar on 14.11.16.
 */
public interface BenefitsService {

    RestMessageDTO addBenefit(BenefitsDTO benefitsDTO, Long id);

    List<BenefitsDTO> getBenefits(Long id);

    RestMessageDTO delBenefitsById(Long idStaff, Long idBen);

    List<BenefitsDTO> createBenefitsDTO(List<Benefits> list);

    List<Benefits> updateBenefits(List<BenefitsDTO> benefits);
}
