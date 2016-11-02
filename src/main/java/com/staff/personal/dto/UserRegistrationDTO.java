package com.staff.personal.dto;

import lombok.Data;

/**
 * Created by mtustanovskyy on 11/1/16.
 */
@Data
public class UserRegistrationDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;


}
