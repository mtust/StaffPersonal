package com.staff.personal.controller.api;

import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mtustanovskyy on 11/2/16.
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserResources {

    @Autowired
    UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }



}
