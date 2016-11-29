package com.staff.personal.repository;

import com.staff.personal.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Staff;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {


//    public List<Staff> findByRegions(Collection<Region> regions);

      public List<Staff> findByIsDeletedTrue();

      public List<Staff> findByIsDeletedFalse();


}
