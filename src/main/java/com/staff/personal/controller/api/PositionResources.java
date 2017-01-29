package com.staff.personal.controller.api;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.nominallyJobBook.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mtustanovskyy on 1/24/17.
 */
@RestController
@Slf4j
@RequestMapping("/api/position")
@CrossOrigin
public class PositionResources {

    @Autowired
    PositionService positionService;

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<Position> getPositions(){
        return positionService.getPositions();
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public RestMessageDTO createPosition(@RequestBody Position position){
        return positionService.createPosition(position);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Position getPosition(@PathVariable(value = "id") Long id){
        return positionService.getPosition(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deletePosition(@PathVariable(value = "id") Long id){
        return positionService.deletePosition(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Position updatePosition(@PathVariable(value = "id") Long id, @RequestBody Position position){
        return positionService.updatePosition(id, position);
    }


}
