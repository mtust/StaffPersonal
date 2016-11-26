package com.staff.personal.repository;

import com.staff.personal.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findById(Long id);

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

}
