package com.staff.personal.service;

import com.staff.personal.domain.User;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;

/**
 * Created by mtustanovskyy on 10/29/16.
 */
@Service
public interface UserService {

    void save(User user);

    UserDTO getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    void createUser(UserRegistrationDTO userRegistrationDTO);

}
