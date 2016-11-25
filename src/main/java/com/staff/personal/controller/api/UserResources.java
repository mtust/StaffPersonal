package com.staff.personal.controller.api;

import com.staff.personal.domain.Role;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by mtustanovskyy on 11/2/16.
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserResources {

    @Autowired
    UserService userService;

    @CrossOrigin
    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
     byte[] getPhoto(@PathVariable  Long id) throws IOException, SQLException {
        log.info("IN CONTROLLER getPhoto");
        return userService.getUserPhoto(id);
    }

    @RequestMapping(value = "{id}/photo", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
     RestMessageDTO changePhoto(@RequestParam("photo") MultipartFile photo,@PathVariable  Long id) throws IOException {
        log.info("IN CONTROLLER changePhoto");
        return userService.changePhoto(photo, id);
    }

}
