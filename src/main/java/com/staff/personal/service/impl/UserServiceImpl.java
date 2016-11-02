package com.staff.personal.service.impl;

import com.staff.personal.domain.Role;
import com.staff.personal.domain.User;
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
        userDTO.setLogin(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleName(user.getRole().name());
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
        if(userRepository.findByEmail(userRegistrationDTO.getUsername()) != null){
            throw new GeneralServiceException("Такий користувач вже існує");
        }
        user.setEmail(userRegistrationDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setRole(Role.ROLE_OPERATOR);
        userRepository.save(user);
    }


}
