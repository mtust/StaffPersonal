package com.staff.personal.service;

import com.staff.personal.domain.Fired;
import com.staff.personal.dto.FiredDTO;
import com.staff.personal.dto.RestMessageDTO;

/**
 * Created by nazar on 16.11.16.
 */
public interface FiredService {

    RestMessageDTO addFired(Long id, FiredDTO firedDTO);

    Fired getFired(Long id);

    RestMessageDTO delFired(Long id);
}
