package com.staff.personal.controller.api.nominallJobBook;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.nominallyJobBooks.Position;
import com.staff.personal.domain.nominallyJobBooks.Rank;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.nominallyJobBook.PositionService;
import com.staff.personal.service.nominallyJobBook.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/rank")
@CrossOrigin
public class RankResources {

    @Autowired
    RankService rankService;

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<Rank> getRank(){
        return rankService.getRank();
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.POST)
    public RestMessageDTO createRank(@RequestBody Rank rank){
        return rankService.createRank(rank);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Rank getRank(@PathVariable(value = "id") Long id){
        return rankService.getRank(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public RestMessageDTO deleteRank(@PathVariable(value = "id") Long id){
        return rankService.deleteRank(id);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Rank updateRank(@PathVariable(value = "id") Long id, @RequestBody Rank rank){
        return rankService.updateRank(id, rank);
    }

}
