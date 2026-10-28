package org.example.repository;

import org.example.entity.RentalRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRequestRepository extends JpaRepository<RentalRequestEntity, Long> {
    List<RentalRequestEntity> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    List<RentalRequestEntity> findAllByOrderByCreatedAtDesc();

    List<RentalRequestEntity> findByStaffIdOrderByCreatedAtDesc(Long staffId);
}
