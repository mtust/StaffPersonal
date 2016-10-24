package com.staff.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.UserPhotos;

public interface UserPhotosRepository extends JpaRepository<UserPhotos, Long> {

}
