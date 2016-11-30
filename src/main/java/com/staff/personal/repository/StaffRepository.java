package com.staff.personal.repository;

import com.staff.personal.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Staff;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {


    public List<Staff> findByIsDeletedFalseAndRegionIn(Collection<Region> regions);

    public List<Staff> findByIsDeletedTrueAndRegionIn(Collection<Region> regions);

    public Staff findOneByIsDeletedFalseAndIdAndRegionIn(Long id, Collection<Region> regions);

    public List<Staff> findByIsDeletedTrue();

    public List<Staff> findByIsDeletedFalse();


}
