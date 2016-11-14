package com.staff.personal.service;

import com.staff.personal.domain.Other;
import com.staff.personal.dto.RestMessageDTO;

/**
 * Created by nazar on 14.11.16.
 */
public interface OtherService {

    RestMessageDTO createOther(Other other, Long id);

    Other getOther(Long id);

    RestMessageDTO delOther(Long id);

}
