package com.staff.personal.repository;

import com.staff.personal.domain.MainStaffPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainStaffPhotosRepository extends JpaRepository<MainStaffPhotos, Long> {

}
