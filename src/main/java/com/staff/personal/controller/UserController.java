package com.staff.personal.controller;

import com.staff.personal.domain.User;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by mtustanovskyy on 10/29/16.
 */


@Path("/api/v1/user")
@CrossOrigin
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserController  {


    @Autowired
    UserService userService;

    @RequestMapping("/{username}")
    public UserDTO getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @RolesAllowed({"ROLE_GUEST"})
    @GET
    public Response getSample(@Context SecurityContext sc) {
        User user = loadUserFromSecurityContext(sc);
        return Response.ok().entity("{\"message\":\"" + user.getEmail() + " is authorized to access\"}").build();
    }




}
