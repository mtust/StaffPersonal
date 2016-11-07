package com.staff.personal.repository;

import com.staff.personal.domain.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotosRepository extends JpaRepository<UserPhoto, Long> {

}
