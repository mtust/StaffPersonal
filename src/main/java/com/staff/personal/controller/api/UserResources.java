package com.staff.personal.controller.api;

import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by mtustanovskyy on 11/2/16.
 */
@Slf4j
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

    @RequestMapping(value = "photo/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
     byte[] getPhoto(@PathVariable  Long id) throws IOException, SQLException {
        log.info("IN CONTROLLER getPhoto");
        return userService.getUserPhoto(id);
    }

    @RequestMapping(value = "photo/{id}", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
     RestMessageDTO changePhoto(@RequestParam("photo") MultipartFile photo,@PathVariable  Long id) throws IOException {
        log.info("IN CONTROLLER changePhoto");
        return userService.changePhoto(photo, id);
    }

}
