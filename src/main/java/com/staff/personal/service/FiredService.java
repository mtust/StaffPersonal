package com.staff.personal.service;

import com.google.gson.JsonObject;
import com.staff.personal.domain.Fired;
import com.staff.personal.dto.FiredDTO;
import com.staff.personal.dto.RestMessageDTO;

import java.util.List;

/**
 * Created by nazar on 16.11.16.
 */
public interface FiredService {

    RestMessageDTO addFired(Long id, FiredDTO firedDTO);

    JsonObject getFired(Long id);

    RestMessageDTO delFired(Long id);

    FiredDTO createFiredDTO(Fired fired);
}
