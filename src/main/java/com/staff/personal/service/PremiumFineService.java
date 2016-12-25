package com.staff.personal.service;

import com.staff.personal.domain.PremiumFine;
import com.staff.personal.dto.PremiumFineDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;

/**
 * Created by nazar on 18.11.16.
 */
public interface PremiumFineService {

    RestMessageDTO addPremiumFine(Long id, PremiumFineDTO premiumFineDTO);

    List<PremiumFine> getPremiumFine(Long id);

    RestMessageDTO delPremiumFine(Long idSt, Long idPrFine);

    List<PremiumFineDTO> createPremiumFineDTO(List<PremiumFine> list);

    List<PremiumFine> updatePremiumFine(List<PremiumFineDTO> premiumFines);
}
