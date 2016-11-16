package com.staff.personal.service;

import com.staff.personal.domain.Region;
import com.staff.personal.domain.User;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.dto.UserRegistrationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by mtustanovskyy on 10/29/16.
 */
@Service
public interface UserService {

    void save(User user);

    UserDTO getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    void createUser(UserRegistrationDTO userRegistrationDTO);

    UserDTO getUserById(Long id);

    byte[] getUserPhoto(Long id) throws SQLException, IOException;

    RestMessageDTO changePhoto(MultipartFile photo, Long id) throws IOException;


}
