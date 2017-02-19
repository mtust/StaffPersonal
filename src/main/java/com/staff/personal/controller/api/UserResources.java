package com.staff.personal.controller.api;

import com.staff.personal.domain.Region;
import com.staff.personal.domain.Role;
import com.staff.personal.domain.User;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.security.Secured;
import com.staff.personal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping(value = "{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    byte[] getPhoto(@PathVariable Long id) throws IOException, SQLException {
        log.info("IN CONTROLLER getPhoto");
        return userService.getUserPhoto(id);
    }

    @RequestMapping(value = "{id}/photo", headers = "content-type=multipart/form-data", method = RequestMethod.PUT)
    RestMessageDTO changePhoto(@RequestParam("photo") MultipartFile photo, @PathVariable Long id) throws IOException {
        log.info("IN CONTROLLER changePhoto");
        return userService.changePhoto(photo, id);
    }

    @RequestMapping(value = "/me")
    UserDTO getMe() {
        return userService.getMe();
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/role", method = RequestMethod.PATCH)
    RestMessageDTO changeUserRole(@PathVariable Long id, String role) {
        return userService.changeUserRole(id, role);
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(value = "{id}/regions", method = RequestMethod.PATCH)
    RestMessageDTO addRegions(@PathVariable Long id, @RequestBody List<Integer> regions) {
        log.info(regions.toString());
        return userService.setRegions(regions, id);
    }

    @RequestMapping(value = "createAdmin", method = RequestMethod.POST)
    public RestMessageDTO createAdmin(){
      return userService.createAdmin();
    }

    @Secured(value = Role.ROLE_ADMIN)
    @RequestMapping(method = RequestMethod.PUT)
    public UserDTO createUser(@RequestBody User user){
        return userService.patchUser(user);
    }
}



