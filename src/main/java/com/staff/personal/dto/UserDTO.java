package com.staff.personal.dto;

import com.staff.personal.domain.Region;
import lombok.Data;

import java.util.Set;

/**
 * Created by mtustanovskyy on 10/30/16.
 */

@Data
public class UserDTO {

    Long id;
    String email;
    String roleName;
    String firstName;
    String lastName;
    Set<Region> regions;

}
