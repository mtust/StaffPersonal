package com.staff.personal.service.impl;

import com.auth0.jwt.internal.org.apache.commons.io.IOUtils;
import com.staff.personal.domain.Role;
import com.staff.personal.domain.User;
import com.staff.personal.domain.UserPhoto;
import com.staff.personal.dto.RestMessageDTO;
import com.staff.personal.dto.UserDTO;
import com.staff.personal.dto.UserRegistrationDTO;
import com.staff.personal.exception.GeneralServiceException;
import com.staff.personal.repository.UserRepository;
import com.staff.personal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.net.URL;
/**
 * Created by mtustanovskyy on 10/29/16.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserDTO userDTO = new UserDTO();
        User user =  userRepository.findByEmail(username);
        userDTO.setRoleName(user.getRole().name());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {

        User user = userRepository.findByEmail(username);

        if(user!= null && !BCrypt.checkpw(password, user.getPassword())){
            log.warn("iмя користувача або пароль неправильні: ");
            throw new GeneralServiceException("iмя користувача або пароль неправильні");
        }

        return user;
    }

    @Override
    public void createUser(UserRegistrationDTO userRegistrationDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        if(!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            throw new GeneralServiceException("Паролі не співпадають");
        }
        if(userRepository.findByEmail(userRegistrationDTO.getEmail()) != null){
            throw new GeneralServiceException("Такий користувач вже існує");
        }
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRole(Role.ROLE_OPERATOR);
        userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setRoleName(user.getRole().name());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        return userDTO;
    }

    @Override
    @Transactional
    public byte[] getUserPhoto(Long id) throws SQLException, IOException {
        User user = userRepository.findById(id);
        if (user.getPhoto() == null) {
            return IOUtils.toByteArray(new URL("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQoiJVlwkYJvPNp7vjnrPPGEe3MDBvcDbaFjkBBjo5_OLlMGLrG_sMtMcCR").openStream());
        }
        log.info(user.toString());
       // InputStream is = user.getPhoto().getPhoto().getBinaryStream();

        return user.getPhoto().getPhoto();
    }


    @Override
    @Transactional
    public RestMessageDTO changePhoto(MultipartFile photo, Long id) throws IOException {
        User user = userRepository.findById(id);
        log.info("IN SERVICE  changePhoto");
        log.info("user: " + user);
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setPhoto(photo.getBytes());
        user.setPhoto(userPhoto);
        return new RestMessageDTO("Success", true);
    }

}
