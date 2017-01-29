package com.staff.personal.repository;

import com.staff.personal.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import com.staff.personal.domain.Staff;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {


    public List<Staff> findByIsDeletedFalseAndIsDeletedByOperatorFalseAndRegionIn(Collection<Region> regions);

    public List<Staff> findByIsDeletedTrueAndIsDeletedByOperatorFalseAndRegionIn(Collection<Region> regions);

    public Staff findOneByIsDeletedFalseAndIsDeletedByOperatorFalseAndIdAndRegionIn(Long id, Collection<Region> regions);

    public List<Staff> findByIsDeletedByOperatorTrueAndRegionIn(Collection<Region> regions);

    public Staff findOneByIdAndRegionIn(Long id, Collection<Region> regions);

    public List<Staff> findByIsDeletedFalse();

    public List<Staff> findByMainStaffPosition(String position);


}
