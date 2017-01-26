package com.staff.personal.service;

import com.staff.personal.domain.Region;
import com.staff.personal.dto.RestMessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by mtustanovskyy on 11/26/16.
 */
@Service
public interface RegionService {

    public RestMessageDTO createRegion(Region region);

    public Region getRegionById(Long id);

    public List<Region> getAllRegions();

    public RestMessageDTO deleteRegionById(Long id);

    RestMessageDTO updateRegion(Long id, Region region);
}
