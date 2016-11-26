package com.staff.personal.service.impl;

import com.staff.personal.domain.Region;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.repository.RegionRepository;
import com.staff.personal.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by mtustanovskyy on 11/26/16.
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionRepository regionRepository;

    @Override
    public RestMessageDTO createRegion(Region region) {
        regionRepository.save(region);
        return new RestMessageDTO("Success", true);
    }

    @Override
    public Region getRegionById(Long id) {
        return regionRepository.findOne(id);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @Override
    public RestMessageDTO deleteRegionById(Long id) {
        regionRepository.delete(id);
        return new RestMessageDTO("Success", true);
    }
}
