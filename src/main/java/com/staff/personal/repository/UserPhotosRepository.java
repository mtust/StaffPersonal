package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.UserPhotos;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPhotosRepository extends JpaRepository<UserPhotos, Long> {

}
