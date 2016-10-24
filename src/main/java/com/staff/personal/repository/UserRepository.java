package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	 User findByEmail(String email);

}
