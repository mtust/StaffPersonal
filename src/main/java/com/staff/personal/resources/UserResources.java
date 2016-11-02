package com.staff.personal.resources;

import com.staff.personal.dto.UserDTO;
import com.staff.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mtustanovskyy on 11/2/16.
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserResources {

    @Autowired
    UserService userService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    String getUserById(@PathVariable Long id) {
        return "ok";
    }

}
