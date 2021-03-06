package com.staff.personal.controller.api;

import com.staff.personal.domain.Region;
import com.staff.personal.domain.Role;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by mtustanovskyy on 11/26/16.
 */
@RequestMapping("/api/region")
@Slf4j
@RestController
@CrossOrigin
public class RegionResources {

    @Autowired
    private RegionService regionService;

//    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public RestMessageDTO createRegion(@RequestBody Region region){
        return regionService.createRegion(region);
    }

//    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Region getRegionById(@PathVariable Long id){
        return regionService.getRegionById(id);
    }

//    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Region> getAllRegions(){
        return regionService.getAllRegions();
    }

//    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deleteRegionById(@PathVariable Long id){
        return regionService.deleteRegionById(id);
    }

//    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public RestMessageDTO updateRegion(@PathVariable Long id, @RequestBody Region region){
        return regionService.updateRegion(id, region);
    }



}
